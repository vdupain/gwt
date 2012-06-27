package fr.generali.ccj.sample.gwt.server.dispatch;

import static org.elasticsearch.index.query.FilterBuilders.geoDistanceRangeFilter;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.filteredQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceRangeFilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Component;

import fr.generali.ccj.sample.gwt.shared.dispatch.GeonameListResult;
import fr.generali.ccj.sample.gwt.shared.dispatch.SearchAction;
import fr.generali.ccj.sample.gwt.shared.dto.FacetDto;
import fr.generali.ccj.sample.gwt.shared.dto.FacetEntryDto;
import fr.generali.ccj.sample.gwt.shared.dto.FacetsDto;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

@Component
public class SearchActionHandler implements ActionHandler<SearchAction, GeonameListResult> {

    private Client client;

    public SearchActionHandler() {
        Settings settings = ImmutableSettings.settingsBuilder().build();
        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    public Class<SearchAction> getActionType() {
        return SearchAction.class;
    }

    public GeonameListResult execute(SearchAction action, ExecutionContext context) throws DispatchException {
        final GeonameListResult result = new GeonameListResult();

        QueryBuilder queryBuilder;
        if (action.getFacets() != null) {
            queryBuilder = buildQueryBuilder(action.getFacets());
        } else {
            queryBuilder = QueryBuilders.fieldQuery("asciiname", action.getPattern());
        }
        System.out.println(queryBuilder);

        SearchResponse searchResponse =
                        client.prepareSearch("geonames").setQuery(queryBuilder)
                            .setFrom(action.getFrom())
                             .setSize(action.getSize()).execute().actionGet();

        SearchHits hits = searchResponse.getHits();
        result.setTotalHits(hits.getTotalHits());

        System.out.println(searchResponse.getTookInMillis());
        System.out.println(searchResponse.getTook());
                
        for (SearchHit hit : hits) {
            result.add(createDtoFromSource(hit.getSource()));
        }
        return result;
    }

    private BoolQueryBuilder buildQueryBuilder(FacetsDto facets) {
        BoolQueryBuilder boolQuery = boolQuery();
        for (FacetDto facetDto : facets.getFacets()) {
            BoolQueryBuilder boolFacetQuery = boolQuery();
            for (FacetEntryDto facetEntryDto : facetDto.getEntries()) {
                if (facetEntryDto.isSelected()) {
                    if (facetDto.getName().equals("geodistance")) {
                        GeoDistanceRangeFilterBuilder filterBuilder =
                                        geoDistanceRangeFilter("location").from(facetEntryDto.getFrom() + "km")
                                                        .to(facetEntryDto.getTo() + "km").point(48.862, 2.353);
                        boolFacetQuery.should(filteredQuery(matchAllQuery(), filterBuilder));
                    } else if (facetDto.getName().equals("population")) {
                        boolFacetQuery.should(rangeQuery(facetDto.getName()).from((long ) facetEntryDto.getFrom()).to(
                                        (long ) facetEntryDto.getTo()));
                    }
                }
            }
            if (boolFacetQuery.hasClauses()) {
                boolQuery.must(boolFacetQuery);
            }
        }
        return boolQuery;
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
            // 2012-01-15T23:00:00.000Z
            geonameDto.setModificationDate(new SimpleDateFormat("yyyy-MM-dd").parse(source.get("modificationDate")
                            .toString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return geonameDto;
    }

    public void rollback(SearchAction action, GeonameListResult result, ExecutionContext context)
                    throws DispatchException {
        throw new RuntimeException();
    }

}
