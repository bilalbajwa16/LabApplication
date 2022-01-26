package lab.pak.com.glacialappportal.Logs

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




class Logs : AppCompatActivity() {
    val uploadlist = ArrayList<log>()
    var json=""

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish()
            // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)
        val toolbar = findViewById<Toolbar>(R.id.header)
        setSupportActionBar(toolbar)


        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)
        val userid = prefs.getString("id", null)

        try {

    populatedata(email, password,userid)
    val toolbar = findViewById<Toolbar>(R.id.header)
    setSupportActionBar(toolbar)

   val actionbar = supportActionBar
    actionbar!!.setDisplayHomeAsUpEnabled(true)

}
catch (e:Exception){
    Toast.makeText(this@Logs, e.toString(), Toast.LENGTH_SHORT).show()


}}



//    glacial.pk/GlacialAppPortalApis/labtestcontents.php?var={"email":"03316151716","password":"admin"}

    ///populating data to form from database////

    fun populatedata(email:String,password:String,userid:String){

        simpleProgressBar.visibility= View.VISIBLE
        val   retrofit = Retrofit.Builder()
            .baseUrl("http://glacial.pk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GetData::class.java)
        val json = "{\"email\":\"$email\",\"password\":\"$password\",\"userid\":\"$userid\"}"
        val obj = JSONObject(json)
        val callPet = service.getlogs("http://glacial.pk/GlacialAppPortalApis/getlogs.php?var=$obj")
        callPet.enqueue(object : Callback<List<log>> {
            override fun onResponse(call: Call<List<log>>, response: Response<List<log>>) {
                try {
                    //       progressBar.setVisibility(View.INVISIBLE);

                    if (response.isSuccessful) {

                        try {

                            val ob = response.body()

                            simpleProgressBar.visibility= View.INVISIBLE
                            val jobs = ArrayList<log>()


                       val s=     ob?.size

                         for(i in 0 until s!!){

    jobs.add(ob?.get(i))
}

                            val u: Int
                            val recyclerView = findViewById<View>(R.id.list) as RecyclerView
// set a LinearLayoutManager with default vertical orientaion
                            val linearLayoutManager = GridLayoutManager(applicationContext, 1)
                            recyclerView.layoutManager =
                                linearLayoutManager // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            val customAdapter = CustomAdapterlogs(
                                this@Logs,
                                ob as ArrayList<log>?
                            )// address,name,amount,idd,lat,lon,phone);
                            //recyclerView.setNestedScrollingEnabled(false)
                            recyclerView.adapter = customAdapter

/*
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
                                    this@Logs,
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
                                    this@Logs,
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
                                    this@Logs,
                                    stepfour
                                )// address,name,amount,idd,lat,lon,phone);
                            recyclerV.setNestedScrollingEnabled(false)
                            recyclerV.adapter = customAdap





                            //             progressBar.setVisibility(View.INVISIBLE);
                            // progressBar.setVisibility(View.INVISIBLE);

  */                      } catch (e: Exception) {

                            //       progressBar.setVisibility(View.INVISIBLE);
                            simpleProgressBar.visibility= View.INVISIBLE

                            //                Toast.makeText(MainActivity.this, "User doesnt Exist", Toast.LENGTH_LONG).show();
                        }

                    }


                } catch (e: Exception) {
                    simpleProgressBar.visibility= View.INVISIBLE
                }

            }

            override fun onFailure(call: Call<List<log>>, t: Throwable) {
                try {
                    simpleProgressBar.visibility= View.INVISIBLE

                    //  progressBar.setVisibility(View.INVISIBLE);

                } catch (a: Exception) {
                    //progressBar.setVisibility(View.INVISIBLE);

                    //e.printStackTrace();
                    // Toast.makeText(MainActivity.this,"Failure"+ a.toString(), Toast.LENGTH_LONG).show();

                }

            }

        })


    }







    override fun onBackPressed() {
       finish()
    }
}
