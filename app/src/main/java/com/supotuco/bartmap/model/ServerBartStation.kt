package com.supotuco.bartmap.model

import com.google.gson.annotations.SerializedName

data class ServerBartStationMetaData(
        @SerializedName("name") val name: String,
        @SerializedName("abbr") val abbreviation: String,
        @SerializedName("gtfs_latitude") val latitudeString: String,
        @SerializedName("gtfs_longitude") val longitudeString: String,
        @SerializedName("address") val rawAddress: String,
        @SerializedName("city") val rawCity: String,
        @SerializedName("county") val rawCounty: String,
        @SerializedName("state") val rawState: String,
        @SerializedName("zipcode") val zipcodeString: String
)

data class ServerBartStations(
        @SerializedName("station") val metaData: List<ServerBartStationMetaData>
)

data class ServerBartStationResponseRoot(
        @SerializedName("stations") val stations: ServerBartStations,
        @SerializedName("message") val message: String
)

data class ServerBartStationResponse(
        @SerializedName("root") val responseRoot: ServerBartStationResponseRoot
)