package nz.pbomb.xposed.anzmods2.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import nz.pbomb.xposed.anzmods2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  // Creates menu
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Determine which MenuItem was pressed and act accordingly based on the button pressed
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.MainActivity__menu_main__Donate:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.Paypal)));
                break;
            case R.id.MainActivity__menu_main__Contact:
                intent = new Intent(getApplicationContext(), ContactActivity.class);
                break;
            case R.id.MainActivity___menu_main__SourceCode:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.SourceCode)));
                break;
            case R.id.MainActivity__menu_main__XDAThread:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.XDAThread)));
                break;
            case R.id.MainActivity__menu_main__About:
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                break;
        }
        startActivity(intent);
        return true;
    }
}
