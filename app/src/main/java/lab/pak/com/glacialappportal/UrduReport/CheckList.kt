package lab.pak.com.glacialappportal.UrduReport

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_lab_test.*
import kotlinx.android.synthetic.main.activity_main.simpleProgressBar
import lab.pak.com.glacialappportal.*

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*




class CheckList : AppCompatActivity() {
    val uploadlist = ArrayList<checklistcontents>()
    var json=""

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish()
            // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }
    fun giveDate() {
        val cal = Calendar.getInstance()
        val msTime = System.currentTimeMillis()

        val sdf = SimpleDateFormat("EEE, MMM d, yyyy  hh:mm")
     date.text=      sdf.format(cal.time)

        //  return sdf.format(cal.time)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)

        try {
    giveDate()
    populatedata(email, password)
    val toolbar = findViewById<Toolbar>(R.id.header)
    setSupportActionBar(toolbar)

   val actionbar = supportActionBar
    actionbar!!.setDisplayHomeAsUpEnabled(true)
     submit.setOnClickListener {

       uploadreport()
       supplimentjson()
    }
}
catch (e:Exception){
//    Toast.makeText(this@ProductionNote, e.toString(), Toast.LENGTH_SHORT).show()


}}

   //Suppiment json for the uploading of data

fun supplimentjson(){

    val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)

    val userid = prefs.getString("id", null)
    val dat: String = date.text.toString()
    json="{'userid':'"+userid+"','dateattribute':'"+dat+"',"

}

//    glacial.pk/GlacialAppPortalApis/labtestcontents.php?var={"email":"03316151716","password":"admin"}

    ///populating data to form from database////

    fun populatedata(email:String,password:String){

        simpleProgressBar.visibility= View.VISIBLE
        val   retrofit = Retrofit.Builder()
            .baseUrl("http://glacial.pk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GetData::class.java)
        val json = "{\"email\":\"$email\",\"password\":\"$password\"}"
        val obj = JSONObject(json)
        val callPet = service.getchecklistcontents("http://glacial.pk/GlacialAppPortalApis/checklistcontents.php?var=$obj")
        callPet.enqueue(object : Callback<List<checklistcontents>> {
            override fun onResponse(call: Call<List<checklistcontents>>, response: Response<List<checklistcontents>>) {
                try {
                    //       progressBar.setVisibility(View.INVISIBLE);

                    if (response.isSuccessful) {

                        try {

                            val ob = response.body()

                            simpleProgressBar.visibility= View.INVISIBLE
                            val jobs = ArrayList<checklistcontents>()
                            val jobschem = ArrayList<checklistcontents>()
                            val jobsbio = ArrayList<checklistcontents>()
                            val stepfour = ArrayList<checklistcontents>()
                            val stepfive = ArrayList<checklistcontents>()


                       val s=     ob?.size
                           // val (id, name, units, glacialths, type) = labtestcontents()

val op= checklistcontents(1,"","","")
//                            for(i in 0 until s!!){
//editlist.add(op)
  //                          }
                         for(i in 0 until s!!){
                             val op= checklistcontents(
                                 1,
                                 ob.get(i).name,
                                 "",
                                 "")
                             uploadlist.add(op)
if(ob?.get(i)?.type.equals("Water Quality")){
    jobs.add(ob?.get(i))
}
                            }
                            val u: Int
                            val recyclerView = findViewById<View>(R.id.stepone) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                            val linearLayoutManager = GridLayoutManager(applicationContext, 1)
                            recyclerView.layoutManager =
                                linearLayoutManager // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            val customAdapter = CustomAdapterchecklist(
                                this@CheckList,
                                jobs
                            )// address,name,amount,idd,lat,lon,phone);
                            recyclerView.setNestedScrollingEnabled(false)
                            recyclerView.adapter = customAdapter


                            for(i in 0 until s!!){

                                if(ob?.get(i)?.type.equals("Shop Readiness")){
                                    jobsbio.add(ob?.get(i))
                                }
                            }
                            //val u: Int
                            val recycle = findViewById<View>(R.id.stepthree) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                            val linearLayoutMa = GridLayoutManager(applicationContext, 1)
                            recycle.layoutManager =
                                linearLayoutMa // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            val customAd =
                                CustomAdapterchecklist(
                                    this@CheckList,
                                    jobsbio

                                )// address,name,amount,idd,lat,lon,phone);
                            recycle.setNestedScrollingEnabled(false)
                            recycle.adapter = customAd




                            for(i in 0 until s!!){

                                if(ob?.get(i)?.type.equals("Shop Cleanliness")){
                                    jobschem.add(ob?.get(i))
                                }
                            }
                            //val u: Int
                            val recyclerVie = findViewById<View>(R.id.steptwo) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                            val linearLayoutManage = GridLayoutManager(applicationContext, 1)
                            recyclerVie.layoutManager =
                                linearLayoutManage // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            val customAdapte =
                                CustomAdapterchecklist(
                                    this@CheckList,
                                    jobschem

                                )// address,name,amount,idd,lat,lon,phone);
                            recyclerVie.setNestedScrollingEnabled(false)
                            recyclerVie.adapter = customAdapte




                            for(i in 0 until s!!){
                                if(ob?.get(i)?.type.equals("Refill Station Preparations")){
                                    stepfour.add(ob?.get(i))
                                }
                            }
                            val recyclerV = findViewById<View>(R.id.stepfour) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                            val linearLayoutMana = GridLayoutManager(applicationContext, 1)
                            recyclerV.layoutManager =
                                linearLayoutMana // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            val customAdap =
                                CustomAdapterchecklist(
                                    this@CheckList,
                                    stepfour
                                )// address,name,amount,idd,lat,lon,phone);
                            recyclerV.setNestedScrollingEnabled(false)
                            recyclerV.adapter = customAdap





                            //             progressBar.setVisibility(View.INVISIBLE);
                            // progressBar.setVisibility(View.INVISIBLE);

                        } catch (e: Exception) {

                            //       progressBar.setVisibility(View.INVISIBLE);
                            simpleProgressBar.visibility= View.INVISIBLE

                            //                Toast.makeText(MainActivity.this, "User doesnt Exist", Toast.LENGTH_LONG).show();
                        }

                    }


                } catch (e: Exception) {
                    simpleProgressBar.visibility= View.INVISIBLE
                }

            }

            override fun onFailure(call: Call<List<checklistcontents>>, t: Throwable) {
                try {
                    simpleProgressBar.visibility= View.INVISIBLE

                    //  progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(this@CheckList, "Sign in error Please try again!", Toast.LENGTH_SHORT).show()
                } catch (a: Exception) {
                    //progressBar.setVisibility(View.INVISIBLE);

                    //e.printStackTrace();
                    // Toast.makeText(MainActivity.this,"Failure"+ a.toString(), Toast.LENGTH_LONG).show();

                }

            }

        })


    }


    //refresh list will add data into json from datalist


fun refreshlist() {

    var index = 0
    var  i=0

    for(i in uploadlist){
    //  Toast.makeText(applicationContext, i.typenumeric, Toast.LENGTH_SHORT)
      //      .show()
    }
    index=0
    val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
    val userid = prefs.getString("id", null)
   // json="{"
   var siz= uploadlist.size
    for (i in uploadlist){
        var ind=index+1
        if(i.urducontent.equals("")){ if (ind < siz) {
            i.urducontent="not entered"
            json = json + "'" + i.name + "':'" + i.urducontent + "',"
        } else {
            i.urducontent="not entered"
            json = json + "'" + i.name + "':'" + i.urducontent + "'}"
        }
        }else {
            if (ind < siz) {
                json = json + "'" + i.name + "':'" + i.urducontent + "',"
            } else {
                json = json + "'" + i.name + "':'" + i.urducontent + "'}"
            }
        }
        index++
    }
}


  //function to upload json data



    fun uploadreport(){
     try {
         simpleProgressBar.visibility = View.VISIBLE
         val retrofit = Retrofit.Builder()
             .baseUrl("http://glacial.pk/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         refreshlist()
         val service = retrofit.create(GetData::class.java)
         //  val json = "{\"email\":\"$email\",\"password\":\"$password\"}"
         val obj = JSONObject(json)
     //    Toast.makeText(applicationContext, json, Toast.LENGTH_LONG)
       //   .show()
         val callPet = service.uploaddata("http://glacial.pk/GlacialAppPortalApis/postchecklist.php?var=$obj")
         callPet.enqueue(object : Callback<Any> {
             override fun onResponse(call: Call<Any>, response: Response<Any>) {
                 try {
                     //       progressBar.setVisibility(View.INVISIBLE);

                     if (response.isSuccessful) {

                         try {
                             val resul = Gson().toJson(response.body())
                             val result = JSONObject(resul)

// get LL json object
                             //val json_LL = json.getJSONObject("LL")

// get value from LL Json Object
                             val value = result.getString("response")
                             simpleProgressBar.visibility = View.INVISIBLE
                             Toast.makeText(applicationContext, value, Toast.LENGTH_SHORT)
                                 .show()

                              finish()
   //                           var o = Intent(this@LabTestActivity, Menu::class.java)
 //                             startActivity(o)
                             //             progressBar.setVisibility(View.INVISIBLE);
                             // progressBar.setVisibility(View.INVISIBLE);

                         } catch (e: Exception) {

                             //       progressBar.setVisibility(View.INVISIBLE);
                             simpleProgressBar.visibility = View.INVISIBLE

                             //                Toast.makeText(MainActivity.this, "User doesnt Exist", Toast.LENGTH_LONG).show();
                         }

                     }


                 } catch (e: Exception) {
                     simpleProgressBar.visibility = View.INVISIBLE
                 }

             }

             override fun onFailure(call: Call<Any>, t: Throwable) {
                 try {
                     simpleProgressBar.visibility = View.INVISIBLE

                     //  progressBar.setVisibility(View.INVISIBLE);

                     Toast.makeText(
                         this@CheckList,
                         "Problem Uploading try again later!",
                         Toast.LENGTH_SHORT
                     ).show()
                 } catch (a: Exception) {
                     //progressBar.setVisibility(View.INVISIBLE);

                     //e.printStackTrace();
                     // Toast.makeText(MainActivity.this,"Failure"+ a.toString(), Toast.LENGTH_LONG).show();

                 }

             }

         })
     }catch (e:Exception){
         simpleProgressBar.visibility = View.INVISIBLE
      //   Toast.makeText(
        // this@LabTestActivity,
        // e.toString(),
       //  Toast.LENGTH_SHORT
    // ).show()
     }

    }

    ///it updates data from recyclerview to activity


    fun update(position: String, s: String,name:String) {
try {





        //val pos:int=position
        val obj = checklistcontents(0, name, "",  s)
        var ij = 0
        var index = 0
        for (i in uploadlist) {

            if (i.urducontent.equals(name)) {
                uploadlist.set(index, obj)
                ij++
            }


            index++
        }
        if (ij <= 0) {
            uploadlist.add(obj)
        }


}catch(e:Exception){
    Toast.makeText(this@CheckList, "Sign in error Please try again!"+e.toString(), Toast.LENGTH_SHORT).show()

}}




    override fun onBackPressed() {
       finish()
    }
}
