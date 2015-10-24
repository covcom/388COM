# AdapterViews and Fragments

Last week we looked at some 'simple' widgets and layouts. Simple here refers to the fact that those widgets are mainly used to display/collect small amount static data. What if the data are of large quantity or dynamically generated? Shall we put say 20 headlines from a newspaper in TextViews one after another? Obviously the answer is no here. Let's find out how.

## Lab 1 AdapterViews

We have looked at Spinner, which is a special case i.e. subclass of AdapterView. In order to make it work, we had to combine three elements: the View itself (Spinner), the data resouse which is typically a Collection (interface), an Adaptor that bines every *single entry* in the Collection with its layout. 

![adaptor components](http://cdn.edureka.co/blog/wp-content/uploads/2013/03/adapters-1.png)

Now let's look at some more subclasses of AdapterView.

### Simple ListView

Follow steps below to create a simple ListView:

1. Insert the following into string.xml. We need to have some data to play with.
    
    ```xml
    <string-array name="candidateNames">
        <item>Hillary Clinton</item>
        <item>Bernie Sanders</item>
        <item>Martin O\'Malley</item>
        <item>Lincoln Chafee</item>
        <item>Donald Trump</item>
        <item>Ben Carson</item>
        <item>Marco Rubio</item>
        <item>Jeb Bush</item>
    </string-array>

    <string-array name="candidateDetails">
        <item>Former US Secretary of State Hillary Clinton (New York)</item>
        <item>US Senator Bernie Sanders (Vermont)</item>
        <item>Former Governor Martin O\'Malley (Maryland)</item>
        <item>Former Governor Lincoln Chafee (Rhode Island)</item>
        <item>Businessman Donald Trump (New York)</item>
        <item>Dr. Ben Carson (Florida)</item>
        <item>US Senator Marco Rubio (Florida)</item>
        <item>Former Governor Jeb Bush (Florida)</item>
    </string-array>
    ```
    
    > There's no Biden - on 21st this month, he anounced he will not run for president in 2016. What a shame!
    
    > For the latest poll, click [here](http://www.realclearpolitics.com/epolls/latest_polls/president/).
    
2. Open content_main.xml, replace the TextView with the following
    
    ```xml
    <ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"></ListView>
    ```
    
3. Open MainActivity.java, insert the following declarations:
    
    ```java
    private ListView listView;
    private String[] candidateNames;
    ```
    
    The three steps above should be simple to understand.
    
4. Again in MainActivity.java, insert the following into the `onCreate()` method
    
    ```java
    candidateNames = getResources().getStringArray(R.array.candidateNames);
    
    listView = (ListView) findViewById(R.id.listView);
    // replace \ with left angle bracket in the line below
    ArrayAdapter\String> arrayAdapter = new ArrayAdapter\String>(this, android.R.layout.simple_list_item_1, candidateNames);
    listView.setAdapter(arrayAdapter);
    listView.setOnItemClickListener(
    
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getBaseContext(), candidateNames[position] + ", seriously?", Toast.LENGTH_SHORT).show();
                }
            }
    );
    ```
    
    What the code above does is to associate the ListView we declared in the layout file with the data we declared in string resource. ArrayAdapter takes three parameters: context, a layout resource for single element of data, and the data. Here `android.R.layout.simple_list_item_1` is a system defined resource layout file that contains only one TextView. You can define you own resource files as you see later on.
    
    > [simple_list_item_1](https://github.com/android/platform_frameworks_base/blob/master/core/res/res/layout/simple_list_item_1.xml) on Github.
    
    Inside `setOnItemClickListener()` block is an anonymous inner class. There're two ways of defining an onClickListener, one is to do `public void doSomething(View v)` and then associate with View's onClick attribute in xml, the other is what you see here. Note here instead of declaring `View.onClick()` for Buttons, which you've seen a lot already, we declared `AdapterView.OnItemClickListener()` wich is specific for AdapterViews. Here 'parent' is the parent view of single data entry, in our case is the ListView, view is the View being clicked, position is the 'index' of current view within the adapter, id is the row id the data entry.
    
    The questionmark inside angle brackets is Java generic wildcard, which basically means the type of parent passed into the method is an AdapterView of any type.
    
    If you run the app, what you'll see is this:
    
    ![simple](.md_images/simple.png)


### Complex ListView

Simple ListView is useful for diplaying data that can be converted to strings in easy steps. But if you want to fine-control the presentation of single entries in your ListView, you need to provide customized layout files for your adapter. This way, you'll make it a 'complex ListView'.

Following steps below to create the data we need later on:

1. Click on this [link](https://github.com/covcom/388COM/blob/master/Week_05_AdapterViews%20and%20Fragments/.md_images/candidates_photos.zip) to go to our GitHub page and the click 'Raw' to download some photos of the candidates. Add those to your drawable resources folder.
2. Create a new class called Candidates and insert the following 
    
    ```java
    public class Candidates {

    private String name;
    private String detail;
    private int photo;

    public Candidates(String name, String detail, int photo) {
        this.name = name;
        this.detail = detail;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public int getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return detail;
    }
    }
    ```

Now we're ready to build our customized ListView.

1. Create a new layout resource file and call it list_item.xml. Insert the following 
    
    ```xml
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal">
    
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="85dp"
        android:layout_height="85dp"
        android:background="@android:color/darker_gray"
        android:padding="8dp"
        android:layout_marginLeft="5dp"
        android:layout_marginTop="5dp"
        android:scaleType="centerCrop" />
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="30dp"
        android:layout_marginTop="20dp"
        android:orientation="vertical">
    
        <TextView
            android:id="@+id/textViewName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="first + last name"
            android:textSize="16dp" />
        
        <TextView
            android:id="@+id/textViewDetail"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:text="details of the candidate"
            android:textSize="12dp" />
    
    </LinearLayout>
    
    </LinearLayout>
    ```
    
    At the moment, your preview window should look like below. This is the layout for a single item in your list.
    
    ![list item](.md_images/listitem.png)
    
2. Create a new 'Empty Activity' called PhotoListActivity. Open activity_photo_list.xml, replace what's in it with the following:
    
    ```xml
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.jianhuayang.mylists.PhotoListActivity">
    
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="2016 Presidential Candidates"
        android:id="@+id/textView"
        android:layout_alignParentTop="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />
    
    <ListView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/listView2"
        android:layout_below="@+id/textView"
        android:layout_marginTop="20dp"/>
    
    </RelativeLayout>
    
    ```
    This layout is very similar to the one we've jut seen.
    
3. Open PhotoListActivity.java, replace the contents of the class with the following
    
    ```java
    
    public static int[] candidatePhotos = {
            R.drawable.clinton,
            R.drawable.sanders,
            R.drawable.omalley,
            R.drawable.chafee,
            R.drawable.trump,
            R.drawable.carson,
            R.drawable.rubio,
            R.drawable.bush
    };
    private String[] candidateNames;
    private String[] candidateDetails;
    // replace \ with angle brackets
    private ArrayList\Candidates> candidates = new ArrayList<>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);

        candidateNames = getResources().getStringArray(R.array.candidateNames);
        candidateDetails = getResources().getStringArray(R.array.candidateDetails);
        generateCandidates();

        listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(new CandidatesAdapter(this, R.layout.list_item, candidates));
        listView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {

                    @Override
                    // replace \ with angle brackets
                    public void onItemClick(AdapterView\?> parent, View view, int position, long id) {

                        Toast.makeText(getBaseContext(), "You clicked " + candidates.get(position), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void generateCandidates() {

        // replace \ with angle brackets
        for (int i = 0; i \ candidatePhotos.length; i++) {
            candidates.add(new Candidates(candidateNames[i], candidateDetails[i], candidatePhotos[i]));
        }
    }
    ```
    
    We declared an array of integers for drawable resources fist, then initialized an ArrayList for our data. The onClickListner is the same as before. The only thing that is new here is the association of data to ListView is through a customized Adapter, as we'll see later. 
    
4. Create a new class called CandidatesAdapter. Open the java file generated and replace its contents with the following lines of code
    
    ```java
    // replace \ with left angle bracket in the line below
    public class CandidatesAdapter extends ArrayAdapter\Candidates>{
    
    private int resource;
    // replace \ with left angle bracket in the line below
    private ArrayList\Candidates> candidates;
    private Context context;
    
    // replace \ with left angle bracket in the line below
    public CandidatesAdapter(Context context, int resource, ArrayList\Candidates> candidates) {
        super(context, resource, candidates);
        this.resource = resource;
        this.candidates = candidates;
        this.context = context;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        try{
            if (v == null){
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = layoutInflater.inflate(resource, parent, false);
            }
            
            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
            TextView textViewName = (TextView) v.findViewById(R.id.textViewName);
            TextView textViewDetail = (TextView) v.findViewById(R.id.textViewDetail);
            
            imageView.setImageResource(candidates.get(position).getPhoto());
            textViewName.setText(candidates.get(position).getName());
            textViewDetail.setText(candidates.get(position).getDetail());
            
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return v;
    }
    
    }
    ```
    
    Its important to understand the code above: our CandidatesAdapter class extends ArrayAdapter of type Candidates. In the constructor we give the layout resource name i.e. the file that contains the custom layout, the one we created earlier. The most important method is getView, where we check if a convertView (i.e. old view) exists or not. If it doesn’t, we’ll need to inflate it. The reason we do this is because ListView recyles its rows when they move out of sreen, instead of creating new ones, to save system resources. 
    
    > [How ListView's recycling mechanism works](http://stackoverflow.com/questions/11945563/how-listviews-recycling-mechanism-works)
    
    There're several different ways of getting an LayoutInflater object:
    
    * The way we did it, `LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)`, is from official [documentation](http://developer.android.com/reference/android/view/LayoutInflater.html).
    * You could use `LayoutInflater inflater = ((Activity)context).getLayoutInflater()`, see an example from [here](http://www.ezzylearning.com/tutorial/customizing-android-listview-items-with-custom-arrayadapter).
    * You could also use `LayoutInflater inflater = (LayoutInflater) CandidatesAdapter.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)`, see an example from [here](http://www.codelearn.org/android-tutorial/android-listview).
    
    Note here `v.findViewById()` is different from `findViewById()`. `v.findViewById()` will only find sub views i.e. views being contained by 'v'; whereas `findViewById()` will find anything contained in the Activity.
    
5. Insert the following into menu_main.xml
    
    ```xml
    <item
        android:id="@+id/action_photo_list"
        android:orderInCategory="10"
        android:title="PhotoList"
        app:showAsAction="always" />
    ```
    
6. Open MainActivity.java, make `onOptionsItemSelected()` look like the following
    
    ```java
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_photo_list:
                startActivity(new Intent(this, PhotoListActivity.class));
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    ```
    
    Steps 5 & 6 are necessary to complete your app, but those two steps should be familiar by now. If you run the app, click on 'PhotoList' in the toolbar, you'll see the following:
    
    ![photo](.md_images/photo.png)

### Grid View

Previous two examples showed you ArrayAdapter and customized ArrayAdapter. In fact, ArrayAdapter is a subclass of BaseAdapter. Let's now go one step further and see how to customized BaseAdapter.

Following steps below to create a GridView and provide a customized BaseAdaptor.

1. Creae a new Empty Activity called GridAcitivty. Open activity_grid.xml and replace what's in it with the following
    
    ```xml
    <GridView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/gridView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:columnWidth="90dp"
    android:gravity="center"
    android:horizontalSpacing="10dp"
    android:numColumns="auto_fit"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:stretchMode="columnWidth"
    android:verticalSpacing="10dp"
    tools:context="com.example.jianhuayang.mylists.GridActivity">
    </GridView>
    ```
    
    There're some new attributes here, but most of them are self-explanatry.
    
2. Open GridActivity.java, replace `onCreate()` with the following. The only thing new here is the Adapter, which we'll look at next.
    ```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getBaseContext(), "At position " + position + " is " + getResources().getStringArray(R.array.candidateNames)[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
    ```
    
3. Create a new class called ImageAdapter, and insert the following
    
    ```java
    public class ImageAdapter extends BaseAdapter {
    
    private int[] candidatePhotos = PhotoListActivity.candidatePhotos;
    private Context context;
    
    public ImageAdapter(Context context) {
        this.context = context;
    }
    
    public int getCount() {
        return candidatePhotos.length;
    }
    
    public Object getItem(int position) {
        return null;
    }
    
    public long getItemId(int position) {
        return 0;
    }
    
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        
        imageView.setImageResource(candidatePhotos[position]);
        return imageView;
    }
    }
    ```
    
    BaseAdapter is the superclass of different Adapters, but it's an abstract class. That means you have to provide concrete implementations for all abstract methods. That's why you see `getItem()` and `getId()` above. Although we don't use those two methods in our example we have to implement them. `getCount()` returen the size of the data. The most important one is still `getView()`. Here instead of using layout resource file, we programically add layout widgets into the parent, and set it's properties. `GridView.LayoutParams()` creates an GridView.LayoutParams object that resides inside the parent view, which is a GridView in our case.
    
5. Insert the following into menu_main.xml
    
    ```xml
    <item
        android:id="@+id/action_grid"
        android:orderInCategory="20"
        android:title="GridView"
        app:showAsAction="always" />
    ```
    
6. Open MainActivity.java, insert the followin case into `onOptionsItemSelected()`
    
    ```java
    case R.id.action_grid:
                startActivity(new Intent(this, GridActivity.class));
                return true;
    ```
    
    If you run the app and click on 'GridView', you'll see something similar to below
    
    ![grid](.md_images/grid.png)

### AdapterView hierarchy

You have seen BaseAdapter and ArrayAdapter, if you Google online examples you'll see more Adapters such as ListAdapter and SimpleAdapter etc. What are the relationships among these?

Basically ArrayAdapter is the first concreate Adapter in the tree, above it are interfaces and an abstract class. But sometimes people do declare something like `ListAdapter listAdapter = new ArrayAdapter<String>()`, don't be confused.

![AdapterViewHierarchy](http://cdn.intertech.com/Blog/wp-content/uploads/2014/06/HeirarchyOfAdapter.png)

A similar hierarchy can be drawn for AdapterView and subclasses. Even though those 'collection' Views are named differently, they are in fact closely related to each other.

![HeirarchyOfAdapter](http://cdn.intertech.com/Blog/wp-content/uploads/2014/06/AdapterViewHierarchy.png)


> Above images from a [blog](http://www.intertech.com/Blog/android-adapters-adapterviews/) written by Jim White.




## Lab 2 Fragments

**Contents**

Fragments, specialized fragments, screen orientation

**Refs**

[Ref.[4]](#ref4) chapter 4



## Lab 3 Advanced topics

For those of you who haven't completed previous labs, you can work on it if you wish. For those who have finished, in this final lab I'll ask some challenging questions for you to explore. These questions are related to previous labs, and somehow involves more efforts to complete.

### Build from command line


![simple](.md_images/simple.png)
