package fr.generali.ccj.sample.gwt.server.dispatch;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.facet.FacetBuilders.geoDistanceFacet;
import static org.elasticsearch.search.facet.FacetBuilders.rangeFacet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.Facets;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.terms.TermsFacet.Entry;
import org.springframework.stereotype.Component;

import fr.generali.ccj.sample.gwt.shared.dispatch.GeonameListAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.GeonameListResult;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

@Component
public class GeonameListActionHandler implements ActionHandler<GeonameListAction, GeonameListResult> {

    private Client client;

    public GeonameListActionHandler() {
        Settings settings = ImmutableSettings.settingsBuilder().build();
        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    public Class<GeonameListAction> getActionType() {
        return GeonameListAction.class;
    }

    public GeonameListResult execute(GeonameListAction action, ExecutionContext context) throws DispatchException {
        final GeonameListResult result = new GeonameListResult();
        SearchResponse searchResponse = client.prepareSearch("geonames")
                            .setQuery(matchAllQuery())                            
                             .setFrom(action.getFrom()).setSize(action.getSize())
                             .execute().actionGet();

//        SearchResponse searchResponse = client.prepareSearch("geonames")
//                        .setQuery(matchAllQuery())
//                        .addFacet(geoDistanceFacet("geo1").field("location").point(40.0, -70.0).unit(DistanceUnit.KILOMETERS)
//                                .addRange(0, 100)
//                                .addRange(100, 500)
//                                .addUnboundedFrom(500)
//                        )
//                        .execute().actionGet();
        
//        SearchResponse searchResponse = client.prepareSearch("geonames")
//                        .setSearchType(SearchType.QUERY_AND_FETCH)
//                        .setQuery(matchAllQuery())
//                        .addFacet(rangeFacet("population").field("population")
//                                      .addRange(1000, 1000000)
//                        )
//                        .addFacet(geoDistanceFacet("geodistance").field("location").point(40.0, -70.0).unit(DistanceUnit.KILOMETERS)
//                                .addRange(100, 500)
//                        )
//                        .execute().actionGet();
        
        SearchHits hits = searchResponse.getHits();
        result.setTotalHits(hits.getTotalHits());

        for (SearchHit hit : hits) {
            result.add(createDtoFromSource(hit.getSource()));
        }
        return result;
    }

    private GeonameDto createDtoFromSource(Map<String, Object> source) {
        GeonameDto geonameDto;
        geonameDto = new GeonameDto();
        geonameDto.setGeonameId(Integer.parseInt(source.get("geonameId").toString()));
        geonameDto.setName(source.get("name").toString());
        geonameDto.setAsciiname(source.get("asciiname").toString());
        geonameDto.setCountryCode(source.get("countryCode").toString());
        geonameDto.setLongitude(Double.parseDouble(source.get("longitude").toString()));
        geonameDto.setLatitude(Double.parseDouble(source.get("latitude").toString()));
        geonameDto.setAdmin1Code(source.get("admin1Code").toString());
        geonameDto.setAdmin2Code(source.get("admin2Code").toString());
        geonameDto.setAdmin3Code(source.get("admin3Code").toString());
        geonameDto.setAdmin4Code(source.get("admin4Code").toString());
        geonameDto.setAlternatenames(source.get("alternatenames").toString());
        geonameDto.setFeatureClass(source.get("featureClass").toString());
        geonameDto.setFeatureCode(source.get("featureCode").toString());
        geonameDto.setCC2(source.get("alternatenames").toString());
        geonameDto.setDem(Integer.parseInt(source.get("dem").toString()));
        geonameDto.setElevation(Integer.parseInt(source.get("elevation").toString()));
        geonameDto.setPopulation(Integer.parseInt(source.get("population").toString()));
        geonameDto.setTimezone(source.get("timezone").toString());
        try {
            //2012-01-15T23:00:00.000Z
            geonameDto.setModificationDate(new SimpleDateFormat("yyyy-MM-dd").parse(source.get("modificationDate").toString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return geonameDto;
    }

    public void rollback(GeonameListAction action, GeonameListResult result, ExecutionContext context)
                    throws DispatchException {
        throw new RuntimeException();
    }

}
