package lab.pak.com.glacialappportal.UrduReport

import com.google.gson.annotations.SerializedName

data class checklistcontents(
  @SerializedName("id") val id: Long,
  @SerializedName("name") var name: String,
  @SerializedName("type") val type: String,
  @SerializedName("urducontent") var urducontent: String



)