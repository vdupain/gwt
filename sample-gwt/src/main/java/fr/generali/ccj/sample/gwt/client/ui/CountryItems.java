package fr.generali.ccj.sample.gwt.client.ui;

import java.util.ArrayList;

public class CountryItems {

    private static final int NUM_ITEMS = 64;

    private static String[] names;

    private static int nameIdx = 0;

    private static ArrayList<Geoname> items = new ArrayList<Geoname>();

    static {
        for (int i = 0; i < NUM_ITEMS; ++i) {
            items.add(createFakeGeoname());
        }
    }

    public static Geoname getGeoname(int index) {
        if (index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    public static int getGeonameCount() {
        return items.size();
    }

    private static Geoname createFakeGeoname() {
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

        Geoname geoname = new Geoname();
        geoname.setName(name);
        geoname.setAsciiname(name);
        geoname.setLatitude(50);
        geoname.setLongitude(50);
        return geoname;
    }
}
