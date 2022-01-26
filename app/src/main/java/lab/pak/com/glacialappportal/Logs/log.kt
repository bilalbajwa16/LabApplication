package lab.pak.com.glacialappportal.Logs

import com.google.gson.annotations.SerializedName

data class log(
  @SerializedName("id") val id: Long,
  @SerializedName("message") var message: String,
  @SerializedName("taskid") val taskid: String,
  @SerializedName("userid") var userid: String,
  @SerializedName("dateattribute") val dateattribute: String,
  @SerializedName("accomplished") var accomplished: String,
  @SerializedName("email") val email: String,
  @SerializedName("formid") var formid: String,
  @SerializedName("formno") val formno: String



  )