package lab.pak.com.glacialappportal


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import lab.pak.com.glacialappportal.Notifications.Adapter
import lab.pak.com.glacialappportal.Notifications.Adapter.RecyclerViewClickListener
import lab.pak.com.glacialappportal.Notifications.ApiInterface
import lab.pak.com.glacialappportal.Notifications.Tasks
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       try{
           setSupportActionBar(header)

           callNotification();

           val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
           val name = prefs.getString("session", null)
 if(name!=null){
  if(name.equals("active")){
      val op = Intent(this@MainActivity, Menu::class.java)
      startActivity(op)
      finish()
  }}



        save.setOnClickListener {

            try {
                login(emailedit.text.toString(), passwordedit.text.toString())
            }catch (e:Exception){    Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
                }

        }
    }catch (e:Exception){    Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
       }}

     private fun callNotification() {


              }

     fun login(email:String,password:String){
         simpleProgressBar.visibility=View.VISIBLE
         val   retrofit = Retrofit.Builder()
             .baseUrl("http://glacial.pk/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()

         val service = retrofit.create(GetData::class.java)
         val json = "{\"email\":\"$email\",\"password\":\"$password\"}"
         val obj = JSONObject(json)
         val callPet = service.getData("http://glacial.pk/applogin.php?var=$obj")
         callPet.enqueue(object : Callback<List<userinfo>> {
             override fun onResponse(call: Call<List<userinfo>>, response: Response<List<userinfo>>) {
                 try {
                     //       progressBar.setVisibility(View.INVISIBLE);

                     if (response.isSuccessful) {

                         try {

                             val ob = response.body()
                             simpleProgressBar.visibility=View.INVISIBLE
                             for (x in ob!!.indices) {
                                 //Toast.makeText(MainActivity.this,ob.get(x).id ,Toast.LENGTH_LONG ).show();
                                 //                                                  Toast.makeText(MainActivity.this,ob.get(x).name ,Toast.LENGTH_LONG ).show();
                                 //                                                Toast.makeText(MainActivity.this,ob.get(x).city ,Toast.LENGTH_LONG ).show();
                                 //                                              Toast.makeText(MainActivity.this,ob.get(x).country ,Toast.LENGTH_LONG ).show();


                             }


                             val editor = getSharedPreferences("userdata", Context.MODE_PRIVATE).edit()
                             val id=ob[0].id.toString()
                             editor.putString("id", id)
                             editor.putString("type", ob[0].type)
                             editor.putString("name", ob[0].name)
                             editor.putString("email", ob[0].email)
                             editor.putString("password", ob[0].password)
                             editor.putString("checklist", ob[0].checklist)
                             editor.putString("productionnote", ob[0].productionnote)
                             editor.putString("labtest", ob[0].labtest)

                             editor.putString("designation", ob[0].designation)
                             editor.putString("date", ob[0].dateattr)
                             editor.putString("email", ob[0].email)
                             editor.putString("session", "active")
                             editor.apply()



                             Toast.makeText(applicationContext, "Signed in!", Toast.LENGTH_SHORT).show()
                             val op = Intent(this@MainActivity, Menu::class.java)
                             startActivity(op)
                             finish()

                             //             progressBar.setVisibility(View.INVISIBLE);
                             // progressBar.setVisibility(View.INVISIBLE);

                         } catch (e: Exception) {

                             //       progressBar.setVisibility(View.INVISIBLE);
                             simpleProgressBar.visibility=View.INVISIBLE

                             //                Toast.makeText(MainActivity.this, "User doesnt Exist", Toast.LENGTH_LONG).show();
                         }

                     }


                 } catch (e: Exception) {
                     simpleProgressBar.visibility=View.INVISIBLE
                 }

             }

             override fun onFailure(call: Call<List<userinfo>>, t: Throwable) {
                 try {
                     simpleProgressBar.visibility=View.INVISIBLE

                     //  progressBar.setVisibility(View.INVISIBLE);

                     Toast.makeText(this@MainActivity, "Sign in error Please try again!", Toast.LENGTH_SHORT).show()
                 } catch (a: Exception) {
                     //progressBar.setVisibility(View.INVISIBLE);

                     //e.printStackTrace();
                     // Toast.makeText(MainActivity.this,"Failure"+ a.toString(), Toast.LENGTH_LONG).show();

                 }

             }

         })


     }
}
