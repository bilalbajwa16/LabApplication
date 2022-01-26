package lab.pak.com.glacialappportal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.nav_header.*
import lab.pak.com.glacialappportal.LabTest.LabTestActivity
import lab.pak.com.glacialappportal.Logs.Logs
import lab.pak.com.glacialappportal.ProductionNote.ProductionNote
import lab.pak.com.glacialappportal.UrduReport.CheckList

class Menu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  override fun onOptionsItemSelected(p0:MenuItem):Boolean
    {
        when (p0.getItemId()) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)  // OPEN DRAWER
                return true
            }
        }
        return super.onOptionsItemSelected(p0)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
    return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
       setSupportActionBar(header)
        val actionbar = supportActionBar
        val toolbar: Toolbar = findViewById(R.id.header)
        setSupportActionBar(toolbar)




        settext()

        imagemain.setOnClickListener {
            val op = Intent(this@Menu, ProfileActivity::class.java)
            startActivity(op)
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        notification.setOnClickListener {  }

        jobsbut.setOnClickListener {
            val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
            val checklist = prefs.getString("productionnote", null)
            if(checklist!=null){
                if(checklist.equals("Y")) {
            val op = Intent(this@Menu, ProductionNote::class.java)
            startActivity(op)
        }else{

                Toast.makeText(this@Menu, "Permission not granted!", Toast.LENGTH_SHORT).show()
        }}}
        aboutbut.setOnClickListener {
            val op = Intent(this@Menu, TaskActivity::class.java)
            startActivity(op)
        }
        notification.setOnClickListener {
            val op = Intent(this@Menu, NotificationList::class.java)
            startActivity(op)

        }

        orderbut.setOnClickListener {
            val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
            val checklist = prefs.getString("labtest", null)
if(checklist!=null){
            if(checklist.equals("Y")) {
    val op = Intent(this@Menu, LabTestActivity::class.java)
    startActivity(op)
}else{

                Toast.makeText(this@Menu, "Permission not granted!", Toast.LENGTH_SHORT).show()
//                Toast.makeText(applicationContext,"Permission not granted!",Toast.LENGTH_SHORT).view
}}
        }
        contactbut.setOnClickListener {
            val op = Intent(this@Menu, Logs::class.java)
            startActivity(op)

        }
      signoutbut.setOnClickListener {
          val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
          val checklist = prefs.getString("checklist", null)
          if(checklist!=null){
              if(checklist.equals("Y")) {
                  val op = Intent(this@Menu, CheckList::class.java)
                  startActivity(op)
              }
      else {
  //            Toast.makeText(applicationContext, "Permission not granted!", Toast.LENGTH_SHORT).view
              Toast.makeText(this@Menu, "Permission not granted!", Toast.LENGTH_SHORT).show()
          }
        }}
        signout.setOnClickListener {
            val editor = getSharedPreferences("userdata", Context.MODE_PRIVATE).edit()
            editor.putString("session", "no")
            editor.apply()
            val op = Intent(this@Menu, MainActivity::class.java)
            startActivity(op)
            drawer_layout.closeDrawer(GravityCompat.START)
            finish()
        }

    }

    private fun Not() {
      //  val notificationList = NotificationList()
      //  notificationList.getPets()

    }

    fun settext(){
        val prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val name = prefs.getString("name", null)
        namet.text=name

        Not()


    }
}
