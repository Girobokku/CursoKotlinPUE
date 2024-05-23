package edu.pue.appcursopue.tabs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import edu.pue.appcursopue.R

class TabsActivity : AppCompatActivity(), TabConfigurationStrategy {

    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var adapterTabs: AdapterTabs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tabs)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.viewPager2 = findViewById<ViewPager2>(R.id.vptl)
        this.tabLayout = findViewById<TabLayout>(R.id.tablayout)
        this.adapterTabs = AdapterTabs(this)

        viewPager2.adapter = adapterTabs
        //ASOCIAR EL TAB CON EL VIEWPAGER
        TabLayoutMediator(tabLayout, viewPager2, this).attach()
    }

    //cuando el tab esté activo, indicamos su texto y apariencia en este método, que será invocado
    override fun onConfigureTab(tab: TabLayout.Tab, pos: Int) {

         tab.setText("VISTA ${pos+1}")
    }
}