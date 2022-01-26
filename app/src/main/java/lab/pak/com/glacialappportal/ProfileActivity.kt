package lab.pak.com.glacialappportal



import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onBackPressed() {
     finish()
    }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(header)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val namet = prefs.getString("name", null)
        val password = prefs.getString("password", null)
        val date = prefs.getString("date", null)
        val phonet = prefs.getString("email", null)
        val designationt = prefs.getString("designation", null)
  if(name!=null){
     name.text=namet}
        if(password!=null){
        passwordtext.text="Password: "+ password
        }else{
            passwordtext.text="Password: "

        }

        if(date!=null){
        datetext.text="Created at: "+date}
        else{
            datetext.text="Created at: "
        }
        if(designation!=null){
designation.text="Designation: "+designationt}else{
            designation.text="Designation: "
        }
        if(phonet!=null){
    phonetext.text="Phone: "+phonet}else{
            phonetext.text="Phone: "
        }
       // val name = prefs.getString("name", null)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
