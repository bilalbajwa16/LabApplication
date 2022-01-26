package lab.pak.com.glacialappportal.LabTest

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
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
import kotlin.collections.ArrayList

class LabList : AppCompatActivity() {
    val uploadlist = ArrayList<labtestcontents>()
    var json=""
    var listnew= ArrayList<Any>()
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

    }

    //main activity function


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test)

      //  val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
  //      val email = prefs.getString("email", null)
    //    val password = prefs.getString("password", null)

        try {
//    giveDate()
    populatedata("03316151716", "admin")
    val toolbar = findViewById<Toolbar>(R.id.header)
    setSupportActionBar(toolbar)


            val actionbar = supportActionBar
    actionbar!!.setDisplayHomeAsUpEnabled(true)
    val b = findViewById<Button>(R.id.home)
//submit.setOnClickListener {
   //uploadreport()
 //   supplimentjson()
  //  }
}
catch (e:Exception){
//    Toast.makeText(this@LabTestActivity, e.toString(), Toast.LENGTH_SHORT).show()


}}


    //list for labtest

    fun getlabtest(ob: List<labtestcontents>?) {
try{

       // simpleProgressBar.visibility= View.VISIBLE
        val   retrofit = Retrofit.Builder()
            .baseUrl("http://glacial.pk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GetData::class.java)

        val callPet = service.getlabtest("http://glacial.pk/api/get_labtest.php")
        callPet.enqueue(object : Callback<List<Any>> {
            override fun onResponse(call: Call<List<Any>>, response: Response<List<Any>>) {
                try {
                    //  progressBar.setVisibility(View.INVISIBLE)
                    //  Toast.makeText(Main.this, response.toString(), Toast.LENGTH_LONG).show();

                    if (response.isSuccessful) {
                       //   Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()

                    }
                    //        Driverinfo ss = response.body();
                    try {
                        //String j= String.valueOf(jsonArray.get(0));
                        val st = response?.body()

                        val yy = st?.size
                        for (i in 0 until yy!!) {
                            val resul = Gson().toJson(st?.get(i))
                            val resu = JSONObject(resul)
                            if (resu != null) {
                                val result = JSONObject(resul)

                                //   Toast.makeText(Main.this, resul.toString(), Toast.LENGTH_LONG).show();
                                val s = ob?.size
                                //  for(i in 0 until s!!){
                              //  Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_LONG).show()

                                listnew.add(result)
                                //result.getString(ob?.get(i).name)
//                                    Toast.makeText(Main.this, driverimage, Toast.LENGTH_LONG).show();


                                ///   progressBar.setVisibility(View.INVISIBLE)
                                //                        dialog.dismiss();
                                //val o = Intent(this@Main, Menu::class.java)

                                //   startActivity(o)}
                            } else {
                                Toast.makeText(
                                    this@LabList,
                                    "Invalid email or password",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // progressBar.setVisibility(View.INVISIBLE)
                            }
                        }

                        val recyclerView = findViewById<View>(R.id.physicallist) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                        val linearLayoutManager = GridLayoutManager(applicationContext, 1)
                        recyclerView.layoutManager =
                            linearLayoutManager // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                        val customAdapter = CustomAdapterLabtTest(
                           this@LabList,
                           ob,response.body()
                        )// address,name,amount,idd,lat,lon,phone);
                        recyclerView.setNestedScrollingEnabled(false)
                        recyclerView.adapter = customAdapter

                    } catch (e: Exception) {

                        //  progressBar.setVisibility(View.INVISIBLE)

                        Toast.makeText(this@LabList, e.toString(), Toast.LENGTH_LONG).show()
                    }

                } catch (e: Exception) {
                    //   progressBar.setVisibility(View.INVISIBLE)
                }

            }

            override fun onFailure(call: Call<List<Any>>, t: Throwable) {
                try {

                    //progressBar.setVisibility(View.INVISIBLE)

                    Toast.makeText(this@LabList, "Error!Check Connection", Toast.LENGTH_LONG).show()
                } catch (a: Exception) {
                //    progressBar.setVisibility(View.INVISIBLE)

                    //e.printStackTrace();
                    Toast.makeText(this@LabList, a.toString(), Toast.LENGTH_LONG).show()

                }

            }


        })


//        Toast.makeText(MainActivity.this,s ,Toast.LENGTH_LONG ).show();


    }catch (e:Exception){
    Toast.makeText(this@LabList, e.toString(), Toast.LENGTH_LONG).show()

}}


    //it will suppliment json string


fun supplimentjson(){

    val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
   // val email = prefs.getString("email", null)
   // val password = prefs.getString("password", null)
    val userid = prefs.getString("id", null)
    val dat: String = date.text.toString()
    val sample: String = samplesourcename.text.toString()
    val chemist: String = chemistname.text.toString()
    val remark: String = remarksname.text.toString()
    val quantity: String = quantityname.text.toString()
    val coll: String = collectedbyname.text.toString()
    json="{'userid':'"+userid+"','dateattribute':'"+dat+"','samplesource':'"+sample+"','chemist':'"+chemist+"','remarks':'"+remark+"','quantity':'"+quantity+"','collectedby':'"+coll+"',"

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
        val callPet = service.getlabtestcontents("http://glacial.pk/GlacialAppPortalApis/labtestcontents.php?var=$obj")
        callPet.enqueue(object : Callback<List<labtestcontents>> {
            override fun onResponse(call: Call<List<labtestcontents>>, response: Response<List<labtestcontents>>) {
                try {
                    //       progressBar.setVisibility(View.INVISIBLE);

                    if (response.isSuccessful) {

                        try {

                            val ob = response.body()
/*
                            simpleProgressBar.visibility= View.INVISIBLE
                            val jobs = ArrayList<labtestcontents>()
                            val jobschem = ArrayList<labtestcontents>()
                            val jobsbio = ArrayList<labtestcontents>()

                          //  val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)

                           // val city = prefs.getString("state", null)

                           // val country = prefs.getString("country", null)


                       val s=     ob?.size
                           // val (id, name, units, glacialths, type) = labtestcontents()

val op= labtestcontents(1, "", "", "", "")
//                            for(i in 0 until s!!){
//editlist.add(op)
  //                          }
                         for(i in 0 until s!!){
                             val op= labtestcontents(
                                 1,
                                 ob.get(i).name,
                                 "",
                                 "",
                                 ""
                             )
                             uploadlist.add(op)
if(ob?.get(i)?.type.equals("physicaltest")){
    jobs.add(ob?.get(i))
}
                            }
                            val u: Int
                            val recyclerView = findViewById<View>(R.id.physicallist) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                            val linearLayoutManager = GridLayoutManager(applicationContext, 1)
                            recyclerView.layoutManager =
                                linearLayoutManager // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            val customAdapter = CustomAdapter(
                                this@LabList,
                                jobs
                            )// address,name,amount,idd,lat,lon,phone);
                            recyclerView.setNestedScrollingEnabled(false)
                            recyclerView.adapter = customAdapter


                            for(i in 0 until s!!){

                                if(ob?.get(i)?.type.equals("chemicaltest")){
                                    jobschem.add(ob?.get(i))
                                }
                            }
                            //val u: Int
                            val recyclerVie = findViewById<View>(R.id.chemicallist) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                            val linearLayoutManage = GridLayoutManager(applicationContext, 1)
                            recyclerVie.layoutManager =
                                linearLayoutManage // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            val customAdapte =
                                CustomAdapterChemical(
                                    this@LabList,
                                    jobschem

                                )// address,name,amount,idd,lat,lon,phone);
                            recyclerVie.setNestedScrollingEnabled(false)
                            recyclerVie.adapter = customAdapte


                            for(i in 0 until s!!){
                                if(ob?.get(i)?.type.equals("microbiologicaltest")){
                                    jobsbio.add(ob?.get(i))
                                }
                            }
                            val recyclerVi = findViewById<View>(R.id.biologylist) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                            val linearLayoutManag = GridLayoutManager(applicationContext, 1)
                            recyclerVi.layoutManager =
                                linearLayoutManag // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            val customAdapt =
                                CustomAdapterBiology(
                                    this@LabList,
                                    jobsbio
                                )// address,name,amount,idd,lat,lon,phone);
                            recyclerVi.setNestedScrollingEnabled(false)
                            recyclerVi.adapter = customAdapt






*/
                            getlabtest(ob)
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

            override fun onFailure(call: Call<List<labtestcontents>>, t: Throwable) {
                try {
                    simpleProgressBar.visibility= View.INVISIBLE

                    //  progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(this@LabList, "Sign in error Please try again!", Toast.LENGTH_SHORT).show()
                } catch (a: Exception) {
                    //progressBar.setVisibility(View.INVISIBLE);

                    //e.printStackTrace();
                    // Toast.makeText(MainActivity.this,"Failure"+ a.toString(), Toast.LENGTH_LONG).show();

                }

            }

        })


    }


    //it will add json data from datalist



fun refreshlist() {
    var index = 0
  //  for (i in editlist) {

      //  if (i.name.equals(uploadlist.get(index).name)) {
        //    uploadlist.set(index, i)
      //  }
      //  index++
    //}
    for(i in uploadlist){
     //   Toast.makeText(applicationContext, i.type, Toast.LENGTH_SHORT)
       //     .show()
    }
    index=0
    val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
    val userid = prefs.getString("id", null)
   // json="{"
   var siz= uploadlist.size
    for (i in uploadlist){
        var ind=index+1
            if(i.type.equals("")){ if (ind < siz) {
                i.type="not entered"
                json = json + "'" + i.name + "':'" + i.type + "',"
            } else {
                i.type="not entered"
                json = json + "'" + i.name + "':'" + i.type + "'}"
            }}else{
                if(ind<siz){

                    json=json+"'"+i.name+"':'"+i.type+"',"}else{
            json=json+"'"+i.name+"':'"+i.type+"'}"
        }}

        index++
    }
}


    ///for uploading data to server

    fun uploadreport(){
     try {
         simpleProgressBar.visibility = View.VISIBLE
         val retrofit = Retrofit.Builder()
             .baseUrl("http://glacial.pk/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         refreshlist()
         //makejson()
         val service = retrofit.create(GetData::class.java)
         //  val json = "{\"email\":\"$email\",\"password\":\"$password\"}"
         val obj = JSONObject(json)
      //   Toast.makeText(applicationContext, json, Toast.LENGTH_LONG)
        //     .show()
         val callPet = service.uploaddata("http://glacial.pk/postlabtest.php?var=$obj")
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
                         this@LabList,
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


    override fun onBackPressed() {
        finish()
    }

    ///for updating data from recyclerview


    fun update(position: Long, s: String,name:String) {
try {
    //val pos:int=position
val obj= labtestcontents(position, name, "", "", s)
    var ij = 0
var index=0;
    for(i in uploadlist) {

     if(i.name.equals(name)){
        uploadlist.set(index,obj)
         ij++}//else{
        /// editlist.add(obj)
    // }
        index++}
    if(ij<=0){
        uploadlist.add(obj)
    }
   // editlist.get(position).type = s
   // editlist.get(position).name = name
 //   val po =position;
     //   // Toast.makeText(this@LabTestActivity, editlist.get(position).name, Toast.LENGTH_SHORT).show()


}catch(e:Exception){

    //Toast.makeText(this@LabTestActivity, "Sign in error Please try again!"+e.toString(), Toast.LENGTH_SHORT).show()

}}
}
