# CycleViewPager
图片自动轮播
#说明
本项目参考[【Android】造轮子：轮播图](http://www.jianshu.com/p/c052ae42fbc9)的内容，具体的原理可以看以上链接。本项目在原来基础上做了一些修改，将一些相互依赖的地方解耦，并做了一些优化。
## 优化之处
- 原文中需要调用CyclerViewpager所在的Activity或者Fragment的静态方法，这样不利于后面的扩展与解耦。代码如下：
```
  private View getImageView(Context context, String url) 
  {
      return MainActivity.getImageView(context, url);
  }
```
本文中的做法是利用借口解耦，做法如下：
/**
     * 获取轮播图View
     *
     * @param context
     * @param url
     */
    private View getImageView(Context context, String url) {
        if (mGetImageViewListener != null) {
            return mGetImageViewListener.getImageView(context, url);
        } else {
            return null;
        }
    }```
    这样虽然增加一些工作，但是能给做到解耦，CycleViewPager不会跟它所在的Activity或者Fragment有直接的方法调用。需要做的setGetImageViewListener并且实现`View getImageView(Context context, String url);`方法。
  
  
- CyclerViewPager中数据源：使用泛型解决不同的数据源entity。使用该控件数据源（Entity）需要继承`CycleViewPager.CycleViewPagerEntity`，这个类里有两个属性：imageUrl和imageTitle，使用时需要将url和title传递给imageUrl和imageTitle（url是必须有，title可以没有），具体使用可以看demo中的实现。虽然有点麻烦，但这也是为了解耦而付出的代价。

## 不足之处
没有解决在某些机型上快速滑动时卡顿的情况，后续有时间再补上。



    



    
