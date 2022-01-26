package lab.pak.com.glacialappportal.ProductionNote

import com.google.gson.annotations.SerializedName

data class productioncontents(
  @SerializedName("id") val id: Long,
  @SerializedName("name") var name: String,
  @SerializedName("step") val step: String,
  @SerializedName("stepname") val stepname: String,
  @SerializedName("inputtype") var inputtype: String,
  @SerializedName("typenumeric") var typenumeric: String,
@SerializedName("namedatabase") var namedatabase: String



)