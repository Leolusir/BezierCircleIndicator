# BezierCircleIndicator

Pager indicator with a magic circle based on bezier curve

## Sample
![image](https://github.com/Leolusir/BezierCircleIndicator/tree/master/gif/sample.gif)

## How to use

add the module `library` to your project

then:

in xml

```
    <com.leo.library.BezierIndicator  
        android:id="@+id/bezier_indicator"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:background="#1d212c"
        app:bi_circle_color="#75c3e2"/>
```

in java

```
    BezierIndicator indicator = (BezierIndicator)findViewById(R.id.bezier_indicator);
    List<TabItem> tabItems = new ArrayList<>();
    ...
    indicator.setTabItems(tabItems);
    indicator.setOnItemSelectedListener(new BezierIndicator.OnItemSelectedListener() {
         @Override
         public void clickItemSelected(int position) {
            viewPager.setCurrentItem(position);
         }
    });
    indicator.invalidate();
```

only text 

![image](https://raw.githubusercontent.com/Leolusir/BezierCircleIndicator/master/image/1.png)


```
    List<TabItem> tabItems = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            TabItem tabItem = new TabItem(this);
            tabItem.setTitle("Tab -" + i);
            tabItem.setTitleSize(13);
            tabItem.setTitleColor(Color.WHITE);
            tabItems.add(tabItem);
        }
    indicator.setTabItems(tabItems);
```

only image 

![image](https://raw.githubusercontent.com/Leolusir/BezierCircleIndicator/master/image/2.png)


```
    //for example
    int[] resIds = {R.drawable.poker_face, R.drawable.joker_face, R.drawable.faker_face, R.drawable.arker_face};
    List<TabItem> tabItems = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            TabItem tabItem = new TabItem(this);
            tabItem.setImageByResId(resIds[i]);
            tabItems.add(tabItem);
        }
    indicator.setTabItems(tabItems);
```

image with text

![image](https://raw.githubusercontent.com/Leolusir/BezierCircleIndicator/master/image/3.png)


```
    //for example
    int[] resIds = {R.drawable.poker_face, R.drawable.joker_face, R.drawable.faker_face, R.drawable.arker_face};
    List<TabItem> tabItems = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            TabItem tabItem = new TabItem(this);
            tabItem.setTitle("Tab -" + i);
            tabItem.setTitleSize(13);
            tabItem.setTitleColor(Color.WHITE);
            tabItem.setImageByResId(resIds[i]);
            tabItems.add(tabItem);
        }
    indicator.setTabItems(tabItems);
```

## Also
You can use the file BezierCircle.java in library directly

```
    <com.leo.library.BezierCircle
        android:id="@+id/bezier_circle"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        app:bc_circle_color="#75c3e2"
        app:bc_visible_count="4"/>
```

## License

```
Copyright(c) 2015 leo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


