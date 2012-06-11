package fr.generali.ccj.sample.gwt.client.ui;

import java.util.ArrayList;

import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class CountryItems {

    private static final int NUM_ITEMS = 64;

    private static String[] names;

    private static int nameIdx = 0;

    private static ArrayList<GeonameDto> items = new ArrayList<GeonameDto>();

    static {
        for (int i = 0; i < NUM_ITEMS; ++i) {
            items.add(createFakeGeoname());
        }
    }

    public static GeonameDto getGeoname(int index) {
        if (index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    public static int getGeonameCount() {
        return items.size();
    }

    private static GeonameDto createFakeGeoname() {
        if (names == null) {
            names = new String[64];
            for (int i = 0; i < NUM_ITEMS; i++) {
                names[i] = "name" + i;
            }
        }

        String name = names[nameIdx++];
        if (nameIdx == names.length) {
            nameIdx = 0;
        }

        GeonameDto geoname = new GeonameDto();
        geoname.setName(name);
        geoname.setAsciiname(name);
        geoname.setLatitude(50);
        geoname.setLongitude(50);
        return geoname;
    }
}
