package lab.pak.com.glacialappportal


import lab.pak.com.glacialappportal.LabTest.labtestcontents
import lab.pak.com.glacialappportal.Logs.log
import lab.pak.com.glacialappportal.ProductionNote.productioncontents
import lab.pak.com.glacialappportal.UrduReport.checklistcontents
import lab.pak.com.glacialappportal.UsersData.user
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetData {
 
//Describe the request type and the relative URL//
@GET
fun getlabtestcontents(@Url  url:String) : Call<List<labtestcontents>>

    @GET
    fun getEmail(@Url url:String) :Call<Any>

   @GET
   fun getData(@Url  url:String) : Call<List<userinfo>>
    @GET
    fun uploaddata(@Url s: String):Call<Any>

    @GET
    fun getproductionnote(@Url s: String): Call<List<productioncontents>>
@GET
fun getuser(@Url s: String): Call<List<user>>

    @GET
    fun getchecklistcontents(@Url s: String): Call<List<checklistcontents>>

    @GET
    fun getlogs(@Url s: String): Call<List<log>>

    @GET
    fun getlabtest(@Url s: String):Call<List<Any>>


}