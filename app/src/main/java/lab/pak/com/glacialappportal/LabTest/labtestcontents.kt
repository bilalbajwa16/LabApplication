package lab.pak.com.glacialappportal.LabTest

import com.google.gson.annotations.SerializedName

data class labtestcontents(
  @SerializedName("id") val id: Long,
  @SerializedName("name") var name: String,
  @SerializedName("units") val units: String,
  @SerializedName("glacialths") val glacialths: String,
  @SerializedName("type") var type: String

)