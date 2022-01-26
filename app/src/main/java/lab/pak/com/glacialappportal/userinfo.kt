package lab.pak.com.glacialappportal

import com.google.gson.annotations.SerializedName

data class userinfo(
  @SerializedName("id") val id: Long,
  @SerializedName("checklist") val checklist: String,
  @SerializedName("labtest") val labtest: String,
  @SerializedName("productionnote") val productionnote: String,

  @SerializedName("name") val name: String,
  @SerializedName("email") val email: String,
  @SerializedName("password") val password: String,
  @SerializedName("dateattr") val dateattr: String,
@SerializedName("designation") val designation: String,
@SerializedName("type") val type: String
)