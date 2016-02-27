package com.akashnarayan.almanac.relationsonline;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

    String[] names = {"Mr. Inspiring Daddy", "Mrs.Loving Mom", "Master Helpful Bro", "Miss.Caring Sister", "Mr.Hardworking Grandpa", "Mrs.Sweet Grandma", "Mr.Strict Uncle", "Mrs.Religious Aunty"};
    String[] relations = {"Father", "Mother", "Brother", "Sister", "GrandFather", "GrandMother", "Uncle", "Aunt"};
    Integer[] ImageIds = {R.drawable.dad, R.drawable.mom, R.drawable.brother, R.drawable.sister, R.drawable.grandfather, R.drawable.grandmother, R.drawable.uncle, R.drawable.aunty};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CustomizedList adptr = new CustomizedList(this,names,relations,ImageIds);
        setListAdapter(adptr);


    }
}
