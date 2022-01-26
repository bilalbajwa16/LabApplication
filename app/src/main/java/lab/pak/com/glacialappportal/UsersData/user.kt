package lab.pak.com.glacialappportal.UsersData

import com.google.gson.annotations.SerializedName

data class user(
  @SerializedName("id") val id: Long,
  @SerializedName("designation") var designation: String,
  @SerializedName("type") val type: String,
  @SerializedName("dateattr") var dateattr: String,
  @SerializedName("password") val password: String,
  @SerializedName("name") var name: String,
  @SerializedName("email") val email: String



  )