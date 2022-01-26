package lab.pak.com.glacialappportal.ProductionNote

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_lab_test.*
import kotlinx.android.synthetic.main.activity_lab_test.date
import kotlinx.android.synthetic.main.activity_lab_test.submit
import kotlinx.android.synthetic.main.activity_main.simpleProgressBar
import kotlinx.android.synthetic.main.activity_productionnote.*
import lab.pak.com.glacialappportal.GetData
import lab.pak.com.glacialappportal.R
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class ProductionNote : AppCompatActivity() {
    private var checko: Int=0
    val uploadlist = ArrayList<productioncontents>()
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
        setContentView(R.layout.activity_productionnote)

        val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)
        val usename = prefs.getString("name",null)
        submitname.text = usename
        try {
    giveDate()
    populatedata(email, password)
    val toolbar = findViewById<Toolbar>(R.id.header)
    setSupportActionBar(toolbar)

   val actionbar = supportActionBar
    actionbar!!.setDisplayHomeAsUpEnabled(true)
     submit.setOnClickListener {
         val pr: SharedPreferences? =
             PreferenceManager.getDefaultSharedPreferences(this)

         val  warning1 = pr?.getString("ProductionE1",null)
         val  warning2 = pr?.getString("ProductionE2",null)
         val  warning3 = pr?.getString("ProductionE3",null)
         val  warning4 = pr?.getString("ProductionE4",null)
         val  warning5 = pr?.getString("ProductionE5",null)

if(warning1!=""||warning2!=""||warning3!=""||warning4!=""||warning5!=""){

 //   Toast.makeText(this@ProductionNote, "email sent", Toast.LENGTH_SHORT).show()

    notifEmail()


}

         uploadreport()
       supplimentjson()
    }
}
catch (e:Exception){
//    Toast.makeText(this@ProductionNote, e.toString(), Toast.LENGTH_SHORT).show()


}}


    // warning email

    fun notifEmail() {
 CustomAdapterProduction.getApiClient("http://glacial.pk/api/mailapi.php/")


    }





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
        val callPet = service.getproductionnote("http://glacial.pk/GlacialAppPortalApis/productionnotecontents.php?var=$obj")
        callPet.enqueue(object : Callback<List<productioncontents>> {
            override fun onResponse(call: Call<List<productioncontents>>, response: Response<List<productioncontents>>) {
                try {
                    //       progressBar.setVisibility(View.INVISIBLE);

                    if (response.isSuccessful) {

                        try {

                            val ob = response.body()

                            simpleProgressBar.visibility= View.INVISIBLE
                            val jobs = ArrayList<productioncontents>()
                            val jobschem = ArrayList<productioncontents>()
                            val jobsbio = ArrayList<productioncontents>()
                            val stepfour = ArrayList<productioncontents>()
                            val stepfive = ArrayList<productioncontents>()


                       val s=     ob?.size
                           // val (id, name, units, glacialths, type) = labtestcontents()

val op= productioncontents(1,"","","","","","")
//                            for(i in 0 until s!!){
//editlist.add(op)
  //                          }
                         for(i in 0 until s!!){
                             val op= productioncontents(
                                 1,
                                 "",
                                 "",
                                 "",
                                 "",
                             "",ob.get(i).namedatabase)
                             uploadlist.add(op)
if(ob?.get(i)?.step.equals("1")){
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
                            val customAdapter = CustomAdapterProduction(
                                this@ProductionNote,
                                jobs
                            )// address,name,amount,idd,lat,lon,phone);
                            recyclerView.setNestedScrollingEnabled(false)
                            recyclerView.adapter = customAdapter


                            for(i in 0 until s!!){

                                if(ob?.get(i)?.step.equals("3")){
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
                                CustomAdapterProduction(
                                    this@ProductionNote,
                                    jobsbio

                                )// address,name,amount,idd,lat,lon,phone);
                            recycle.setNestedScrollingEnabled(false)
                            recycle.adapter = customAd




                            for(i in 0 until s!!){

                                if(ob?.get(i)?.step.equals("2")){
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
                                CustomAdapterProduction(
                                    this@ProductionNote,
                                    jobschem

                                )// address,name,amount,idd,lat,lon,phone);
                            recyclerVie.setNestedScrollingEnabled(false)
                            recyclerVie.adapter = customAdapte




                            for(i in 0 until s!!){
                                if(ob?.get(i)?.step.equals("4")){
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
                                CustomAdapterProduction(
                                    this@ProductionNote,
                                    stepfour
                                )// address,name,amount,idd,lat,lon,phone);
                            recyclerV.setNestedScrollingEnabled(false)
                            recyclerV.adapter = customAdap




                            for(i in 0 until s!!){
                                if(ob?.get(i)?.step.equals("5")){
                                    stepfive.add(ob?.get(i))
                                }
                            }
                            val recy = findViewById<View>(R.id.stepfive) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                            val linearLayoutMan = GridLayoutManager(applicationContext, 1)
                            recy.layoutManager =
                                linearLayoutMan // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            val customAda =
                                CustomAdapterProduction(
                                    this@ProductionNote,
                                    stepfive
                                )// address,name,amount,idd,lat,lon,phone);
                            recy.setNestedScrollingEnabled(false)
                            recy.adapter = customAda


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

            override fun onFailure(call: Call<List<productioncontents>>, t: Throwable) {
                try {
                    simpleProgressBar.visibility= View.INVISIBLE

                    //  progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(this@ProductionNote, "Sign in error Please try again!", Toast.LENGTH_SHORT).show()
                } catch (a: Exception) {
                    //progressBar.setVisibility(View.INVISIBLE);

                    //e.printStackTrace();
                    // Toast.makeText(MainActivity.this,"Failure"+ a.toString(), Toast.LENGTH_LONG).show();

                }

            }

        })


    }


    //refresh list will add data into json from datalist


fun refreshlist(): Int {
 checko =0
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
        if(i.typenumeric.equals("")){ if (ind < siz) {
            i.typenumeric="not entered"
          checko++
            json = json + "'" + i.namedatabase + "':'" + i.typenumeric + "',"
        } else {
            i.typenumeric="not entered"
            json = json + "'" + i.namedatabase + "':'" + i.typenumeric + "'}"
        checko++
        }
        }else {
            if (ind < siz) {
                json = json + "'" + i.namedatabase + "':'" + i.typenumeric + "',"
            } else {
                json = json + "'" + i.namedatabase + "':'" + i.typenumeric + "'}"
            }
        }
        index++
    }
    return checko
}


  //function to upload json data



    fun uploadreport(){
     try {
         simpleProgressBar.visibility = View.VISIBLE
         val retrofit = Retrofit.Builder()
             .baseUrl("http://glacial.pk/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         var checko=refreshlist()
         if(checko==0) {
             //makejson()
             val service = retrofit.create(GetData::class.java)
             //  val json = "{\"email\":\"$email\",\"password\":\"$password\"}"
             val obj = JSONObject(json)
             //   Toast.makeText(applicationContext, json, Toast.LENGTH_LONG)
             //  .show()
             val callPet =
                 service.uploaddata("http://glacial.pk/GlacialAppPortalApis/postproductionnote.php?var=$obj")
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
                             this@ProductionNote,
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
         }else{ Toast.makeText(
             this@ProductionNote,
             "Enter data in all fields!",
             Toast.LENGTH_SHORT
         ).show()

             simpleProgressBar.visibility = View.INVISIBLE
         }}catch (e:Exception){
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

    if(position.equals("79299")){
      //  val pos:int=position

        var ij = 0
        var index=0
        for(i in uploadlist) {

            if(i.namedatabase.equals(name)){
                ij++

                if(uploadlist.get(index).typenumeric.equals("")) {
                    val ss=s+","
                    val obj= productioncontents(1, "", "", "", "",ss,name)

                    uploadlist.set(index,obj)

                }else{
                    var sam=uploadlist.get(index).typenumeric
                    val animalsArray = sam.split(",")
                    val ss=s+","+animalsArray[1]
                    val obj= productioncontents(0, "", "", "", "",ss,name)

                    uploadlist.set(index,obj)
                }}


            index++}
        if(ij<=0){
            val ss=s+","
            val obj= productioncontents(0, "", "", "", "",ss,name)

            uploadlist.add(obj)
        }

    }


   else if(position.equals("79294")){
        //  val pos:int=position

        var ij = 0
        var index=0
        for(i in uploadlist) {

            if(i.namedatabase.equals(name)){
                ij++

                if(uploadlist.get(index).typenumeric.equals("")) {
                    val ss=","+s
                    val obj= productioncontents(0, "", "", "", "",ss,name)

                    uploadlist.set(index,obj)
//                    ij++

                }else{

                   // if(uploadlist.get(index).typenumeric.indexOf(",")!=-1) {
                        var sam = uploadlist.get(index).typenumeric
                        val animalsArray = sam.split(",")
                        val ss= animalsArray[0] + "," + s
                    val obj= productioncontents(0, "", "", "", "",ss,name)

                        uploadlist.set(index, obj)
                   // }else{

                   // }

                    }
                }


            index++}
        if(ij<=0){

            val ss=","+name
            val obj= productioncontents(0, "", "", "", "",s,ss)

            uploadlist.add(obj)
        }

    }


else {

        //val pos:int=position
        val obj = productioncontents(0, "", "", "", "", s, name)
        var ij = 0
        var index = 0
        for (i in uploadlist) {

            if (i.namedatabase.equals(name)) {
                uploadlist.set(index, obj)
                ij++
            }


            index++
        }
        if (ij <= 0) {
            uploadlist.add(obj)
        }

    }
}catch(e:Exception){
    Toast.makeText(this@ProductionNote, "Sign in error Please try again!"+e.toString(), Toast.LENGTH_SHORT).show()

}}




    override fun onBackPressed() {
       finish()
    }
}
