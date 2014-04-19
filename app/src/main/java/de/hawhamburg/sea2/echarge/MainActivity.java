package de.hawhamburg.sea2.echarge;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import de.hawhamburg.sea2.echarge.library.MenuListAdapter;

public class MainActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;

    private String[] drawerTitles;
    private String[] drawerSubtitles;
    private int[] drawerIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Hole die Titel aus einem Array aus der strings.xml
        drawerTitles = getResources().getStringArray(R.array.drawerTitles_array);
        // Hole die Untertitel aus einem Array aus der strings.xml
        drawerSubtitles = getResources().getStringArray(R.array.drawerSubtitles_array);
        // Setzt die Icons zu den Einträgen
        drawerIcons = new int[] {android.R.drawable.ic_menu_info_details, android.R.drawable.ic_menu_edit, android.R.drawable.ic_menu_delete};

        // Erstellt den neuen MenuAdapter aus der Klasse MenuListAdapter
        MenuListAdapter mMenuAdapter = new MenuListAdapter(this, drawerTitles, drawerSubtitles, drawerIcons);
        mDrawerList.setAdapter(mMenuAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Fügt den Navigation Drawer zur ActionBar hinzu
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
            //    invalidateOptionsMenu();
                //supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(R.string.app_name);
             //   invalidateOptionsMenu();
             //   supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Bereitet die ActionBar auf den Navigation Drawer vor
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    // Fügt das Menü hinzu / ActionBar Einträge
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Versteckt die ActionBar-Einträge, sobald der Drawer ausgefahren is
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_kp).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Öffnet und schließt den Navigation Drawer bei Klick auf den Titel/das Icon in der ActionBar
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }

        // Gibt den ActionBar-Buttons Funktionen
        switch (item.getItemId()) {
            case R.id.action_kp:
                // Tu' etwas!
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Listener für die Navigation Drawer Einträge - Achtung: Zählung beginnt bei 0!
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                // Aktion
            } else if (position == 1) {
                // Aktion
            } else if (position == 2) {
                // Aktion
            }

            mDrawerList.setItemChecked(position, true);
            mTitle = drawerTitles[position];
            getActionBar().setTitle(mTitle);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }



    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        System.exit(0);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        // your code.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Möchten Sie die Anwendung wirklich beenden?").setPositiveButton("Ja", dialogClickListener)
                .setNegativeButton("Nein", dialogClickListener).show();
    }

}
