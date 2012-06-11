package fr.generali.ccj.sample.gwt.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class GeonameDto implements Serializable {

    private int geonameId;

    private String name;

    private String asciiname;

    private Date modificationDate;

    private String timezone;

    private int dem;

    private int elevation;

    private int population;

    private String admin4Code;

    private String admin3Code;

    private String admin2Code;

    private String admin1Code;

    private String cc2;

    private String countryCode;

    private String featureCode;

    private String featureClass;

    private double longitude;

    private double latitude;

    private String alternatenames;

    public void setGeonameId(int geonameId) {
        this.geonameId = geonameId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAsciiname(String asciiname) {
        this.asciiname = asciiname;
    }

    public void setAlternatenames(String alternatenames) {
        this.alternatenames = alternatenames;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setFeatureClass(String featureClass) {
        this.featureClass = featureClass;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCC2(String cc2) {
        this.cc2 = cc2;
    }

    public void setAdmin1Code(String admin1Code) {
        this.admin1Code = admin1Code;
    }

    public void setAdmin2Code(String admin2Code) {
        this.admin2Code = admin2Code;
    }

    public void setAdmin3Code(String admin3Code) {
        this.admin3Code = admin3Code;
    }

    public void setAdmin4Code(String admin4Code) {
        this.admin4Code = admin4Code;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public void setDem(int dem) {
        this.dem = dem;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getGeonameId() {
        return geonameId;
    }

    public String getName() {
        return name;
    }

    public String getAsciiname() {
        return asciiname;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getDem() {
        return dem;
    }

    public int getElevation() {
        return elevation;
    }

    public int getPopulation() {
        return population;
    }

    public String getAdmin4Code() {
        return admin4Code;
    }

    public String getAdmin3Code() {
        return admin3Code;
    }

    public String getAdmin2Code() {
        return admin2Code;
    }

    public String getAdmin1Code() {
        return admin1Code;
    }

    public String getCc2() {
        return cc2;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public String getFeatureClass() {
        return featureClass;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAlternatenames() {
        return alternatenames;
    }

}
