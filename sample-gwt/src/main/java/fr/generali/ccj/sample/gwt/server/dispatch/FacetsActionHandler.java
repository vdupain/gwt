package fr.generali.ccj.sample.gwt.server.dispatch;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.facet.FacetBuilders.geoDistanceFacet;
import static org.elasticsearch.search.facet.FacetBuilders.rangeFacet;

import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.Facets;
import org.elasticsearch.search.facet.geodistance.GeoDistanceFacet;
import org.elasticsearch.search.facet.range.RangeFacet;
import org.springframework.stereotype.Component;

import fr.generali.ccj.sample.gwt.shared.dispatch.FacetsAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.FacetsResult;
import fr.generali.ccj.sample.gwt.shared.dto.FacetDto;
import fr.generali.ccj.sample.gwt.shared.dto.FacetsDto;

@Component
public class FacetsActionHandler implements ActionHandler<FacetsAction, FacetsResult> {

    private Client client;

    public FacetsActionHandler() {
        Settings settings = ImmutableSettings.settingsBuilder().build();
        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    public Class<FacetsAction> getActionType() {
        return FacetsAction.class;
    }

    public FacetsResult execute(FacetsAction action, ExecutionContext context) throws DispatchException {
        SearchResponse searchResponse =
                        client.prepareSearch("geonames")
                                        .setSearchType(SearchType.QUERY_AND_FETCH)
                                        .setQuery(matchAllQuery())
                                        .addFacet(rangeFacet("population").field("population").addRange(0, 1000)
                                                        .addRange(1000, 10000)
                                                        .addRange(10000, 100000)
                                                        .addRange(100000, 1000000)
                                                        .addRange(1000000, 10000000)
                                                        .addUnboundedTo(10000000))
                                        .addFacet(geoDistanceFacet("geodistance").field("location").point(48.862, 2.353)
                                                        .unit(DistanceUnit.KILOMETERS).addRange(0, 100)
                                                        .addRange(100, 500).addRange(500, 1000).addRange(1000, 10000)
                                                        .addUnboundedTo(10000)).execute().actionGet();

        final FacetsResult result = new FacetsResult();
        result.setFacets(buildFacetsDto(searchResponse.facets()));
        return result;
    }

    private FacetsDto buildFacetsDto(Facets facets) {
        FacetsDto facetsDto = new FacetsDto();
        for (Facet facet : facets) {
            if ("population".equals(facet.getName())) {
                FacetDto facetDto = new FacetDto(facet.getName());
                List<RangeFacet.Entry> entries = ((RangeFacet ) facet).getEntries();
                for (RangeFacet.Entry entry : entries) {
                    facetDto.addEntry(entry.getFrom(), entry.getTo(), entry.getCount());
                }
                facetsDto.add(facetDto);
            } else if ("geodistance".equals(facet.getName())) {
                FacetDto facetDto = new FacetDto(facet.getName());
                GeoDistanceFacet geoDistanceFacet = (GeoDistanceFacet) facet;
                List<GeoDistanceFacet.Entry> entries = geoDistanceFacet.getEntries();                
                for (GeoDistanceFacet.Entry entry : entries) {
                    facetDto.addEntry(entry.getFrom(), entry.getTo(), entry.getCount());
                }
                facetsDto.add(facetDto);
            }
        }
        return facetsDto;
    }

    public void rollback(FacetsAction action, FacetsResult result, ExecutionContext context) throws DispatchException {
        throw new RuntimeException();
    }

}
