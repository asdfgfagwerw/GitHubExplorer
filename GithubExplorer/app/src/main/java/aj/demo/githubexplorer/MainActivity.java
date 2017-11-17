package aj.demo.githubexplorer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import aj.demo.githubexplorer.enterLanguage.EnterLanguageFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchEnterLanguageFragment();
    }

    private void launchEnterLanguageFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_container, new EnterLanguageFragment(), EnterLanguageFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    public void launchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, fragment.getTag())
                .addToBackStack(fragment.getTag())
                .commit();
    }
}
