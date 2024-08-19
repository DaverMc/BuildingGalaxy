package de.daver.build.hub.api.util;

public enum MinecraftVersion {

    // Major updates where new items were added
    RELEASE_1_0("1.0", "November 18, 2011"),
    RELEASE_1_1("1.1", "January 12, 2012"),
    RELEASE_1_2_1("1.2.1", "March 1, 2012"),
    RELEASE_1_3_1("1.3.1", "August 1, 2012"),
    RELEASE_1_4_2("1.4.2", "October 25, 2012"),
    RELEASE_1_4_6("1.4.6", "December 20, 2012"),
    RELEASE_1_5("1.5", "March 13, 2013"),
    RELEASE_1_6_1("1.6.1", "July 1, 2013"),
    RELEASE_1_7_2("1.7.2", "October 25, 2013"),
    RELEASE_1_8("1.8", "September 2, 2014"),
    RELEASE_1_9("1.9", "February 29, 2016"),
    RELEASE_1_10("1.10", "June 8, 2016"),
    RELEASE_1_11("1.11", "November 14, 2016"),
    RELEASE_1_12("1.12", "June 7, 2017"),
    RELEASE_1_13("1.13", "July 18, 2018"),
    RELEASE_1_14("1.14", "April 23, 2019"),
    RELEASE_1_15("1.15", "December 10, 2019"),
    RELEASE_1_16("1.16", "June 23, 2020"),
    RELEASE_1_17("1.17", "June 8, 2021"),
    RELEASE_1_18("1.18", "November 30, 2021"),
    RELEASE_1_19("1.19", "June 7, 2022"),
    RELEASE_1_20("1.20", "June 7, 2023");

    private final String version;
    private final String releaseDate;

    MinecraftVersion(String version, String releaseDate) {
        this.version = version;
        this.releaseDate = releaseDate;
    }

    public String getVersion() {
        return this.version;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }
}

