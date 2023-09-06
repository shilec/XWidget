# XWidget

[![](https://jitpack.io/v/shilec/XWidget.svg)](https://jitpack.io/#shilec/XWidget)

### 一个轻量级的扩展的原生控件库，通过自定义属性的方式，解析为目标drawable。减少了自定义Drawable文件的生成。实现简单，使用简单。自定义View只是一个载体，View中无任何自定义绘制，完全由原生Drawable实现，不用担心性能损耗。

## 示例
<img src="images/1.gif"/>

### 使用示例

> `XWidget`提供了丰富的属性，属性名称基本上和手写Drawable文件中所涉及的属性一致，所以不需要特殊记忆，即写即用。不过在使用前需要理解`XWidget`中的一个概念，`stated`。包含有`stated`的属性表示，只有当当前控件的状态和`XWidget_state`一致时，才会触发。 `XWidget_state`其实对应的就是我们通常编写`selector`时所指定的状态。例如：想实现一个TextView在按压时，背景变为红色，圆角度数为`8dp`。你可以这样写：
 ```
    <com.scott.xwidget.widget.XTextView
            android:layout_gravity="center"
            android:id="@+id/v_test2"
            app:XTextView_corner="8dp"
            app:XTextView_solid_color="#ffffff"
            app:XTextView_stated_solid_color="#FF0000"
            android:text="FUCK"
            app:XTextView_state="pressed"
            android:gravity="center"
            android:textSize="22sp"
            android:layout_width="100dp"
            android:layout_height="100dp"/>
 ```
> 如果想当TextView被`select`时，背景颜色切换，可以这样实现：

```
   <com.scott.xwidget.widget.XTextView
            android:layout_gravity="center"
            android:id="@+id/v_test2"
            app:XTextView_corner="8dp"
            app:XTextView_state="selected"
            app:XTextView_solid_color="#ffffff"
            app:XTextView_stated_solid_color="#FF0000"
            android:text="FUCK"
            android:gravity="center"
            android:textSize="22sp"
            android:layout_width="100dp"
            android:layout_height="100dp"/>
```

## 支持的控件
`XButton, XConstraintLayout, XEditText, XFrameLayout, XImageView, XLinearLayout, XRelativeLayout, XTextView, XCheckBox, XView, XRadioButton `...

> 如果没有自己想要的控件，可以按照下面的 "扩展 2" 配置gradle插件，一键生成。

#### 集成

1. 在project build.gradle 中加入

        allprojects {
            repositories {
                maven { url 'https://jitpack.io' }
            }
        }
> 注意: 如果AS中无法实时预览，请升级android构建插件的版本至`classpath 'com.android.tools.build:gradle:4.1.2'`或者以上, 同时升级至对应的gradle版本。

2. 在要使用的module 的 build.gradle 中加入

         implementation 'com.github.shilec:XWidget:1.3.5'

3. 添加混淆

        -keep public class com.scott.xwidget.parser.**{*;}


#### 扩展

1. 自定义解析器

        XWidget.addParser(XButton::class.java, XButtonParser2())


2. 扩展自定义View

    2.1 自定义View增加注解
    ```
        // 这里的包名是你的R文件的包名，例如: 你的R文件为 com.scott.demo.R; ,这里就是 com.scott.demo
        @XWidgetView("com.example.viewdemo")
        class XButtonCustom(context: Context, attrs: AttributeSet?) : AppCompatButton(context, attrs) {
            init {
                XWidget.inject(this, attrs)
            }
        }
    ```

    2.2 要创建自定义View的module下的build.gradle配置

    ```
        plugins {
            ...... // 省略其他配置
            id 'kotlin-kapt'
            id 'com.scott.xwidget-gradle-plugin'
        }

        depenencies {
              ...... // 省略其他配置
              implementation 'com.github.shilec:XWidget:1.3.5'
            kapt('com.github.shilec:XWidget:1.3.5')
        }
    ```

    2.3 根build.gradle配置插件
    ```
           dependencies {
                ...... // 省略其他配置
                classpath 'com.github.shilec.XWidget:xwidget-gradle-plugin:1.3.5'
            }
    ```


    2.4 选择module, Build -> Make module xxx。在values下将生成自定义View的属性，`xwidget_attrs.xml`, 此时就可以在布局中直接引用 `XButtonCustom` 啦。


## 支持属性名称

> 上面列出的控件都支持下表所列的属性，通过gradle插件一键生成的自定义View也支持这些属性。此处仅以XButtonCustom举例。

| 属性名称 | 示例
--------| ---
**XButtonCustom_ripple_enable**|**允许按压波纹**
**XButtonCustom_ripple_color**|**按压波纹颜色**
**XButtonCustom_corner**|**圆角度数**
**XButtonCustom_corner_type**|**圆角类型，默认 all。left_top,left_bottom,right_top,right_bottom**
**XButtonCustom_solid_color**|**背景填充颜色，当没有设置渐变填充时生效**
**XButtonCustom_shadow_color**|**外阴影色值**
**XButtonCustom_shadow_radius**|**外阴影半径**
**XButtonCustom_shadow_dx**|**外阴影x方向偏移**
**XButtonCustom_shadow_dy**|**外阴影y方向偏移**
**XButtonCustom_solid_gradient**|**渐变类型，linear, radial, sweep**
**XButtonCustom_gradient_start_color**|**渐变起始颜色**
**XButtonCustom_gradient_center_color**|**渐变中间颜色**
**XButtonCustom_gradient_end_color**|**渐变结束颜色**
**XButtonCustom_gradient_orientation**|**渐变的方向**
**XButtonCustom_gradient_radius**|**渐变角度**
**XButtonCustom_stroke_color**|**描边颜色**
**XButtonCustom_stroke_border**|**描边宽度**
**XButtonCustom_stroke_gradient_start_color**|**描边渐变起始颜色**
**XButtonCustom_stroke_gradient_end_color**|**描边渐变结束颜色**
**XButtonCustom_stroke_gradient_middle_color**|**描边渐变中间颜色**
**XButtonCustom_stroke_gradient_offset_x**|**描边渐变Linear shader 起始x (参见: LinearGradient 参数, 默认为0)**
**XButtonCustom_stroke_gradient_offset_x1**|**描边渐变Linear shader 截止x1 (参见: LinearGradient 参数, 默认为视图宽度)**
**XButtonCustom_stroke_gradient_offset_y**|**描边渐变Linear shader 起始y (参见: LinearGradient 参数, 默认为0 )**
**XButtonCustom_stroke_gradient_offset_y1**|**描边渐变Linear shader 截止y1 (参见: LinearGradient 参数, 默认为0 )**
**XButtonCustom_blur_type**|**模糊类型，默认为normal。outer,solid,inner**
**XButtonCustom_blur_radius**|**模糊半径**
**XButtonCustom_blur_color**|**模糊颜色**
**XButtonCustom_text_color**|**文本颜色**
**XButtonCustom_color_filter**|**颜色过滤器**
**XButtonCustom_state**|**selector类型，默认none。pressed, selected, checked。选定上述状态时，触发state前缀的属性**
**XButtonCustom_stated_text_color**|**文本颜色**
**XButtonCustom_stated__color_filter**|**颜色过滤器**
**XButtonCustom_stated_corner**|**state触发的圆角**
**XButtonCustom_corner_type**|**state触发时的圆角类型，默认 all。left_top,left_bottom,right_top,right_bottom**
**XButtonCustom_stated_solid_color**|**state出发时背景填充颜色，当没有设置渐变填充时生效**
**XButtonCustom_stated_shadow_color**|**外阴影色值**
**XButtonCustom_stated_shadow_radius**|**外阴影半径**
**XButtonCustom_stated_shadow_dx**|**外阴影x方向偏移**
**XButtonCustom_stated_shadow_dy**|**外阴影y方向偏移**
**XButtonCustom_stated_blur_type**|**模糊类型，默认为normal。outer,solid,inner**
**XButtonCustom_stated_blur_radius**|**模糊半径**
**XButtonCustom_stated_blur_color**|**模糊颜色**
**XButtonCustom_stated_solid_gradient**|**渐变类型，linear, radial, sweep**
**XButtonCustom_stated_gradient_start_color**|**渐变起始颜色**
**XButtonCustom_stated_gradient_center_color**|**渐变中间颜色**
**XButtonCustom_stated_gradient_end_color**|**渐变结束颜色**
**XButtonCustom_stated_gradient_orientation**|**渐变的方向**
**XButtonCustom_stated_gradient_radius**|**渐变角度**
**XButtonCustom_stated_stroke_color**|**描边颜色**
**XButtonCustom_stated_stroke_border**|**描边宽度**
**XButtonCustom_stated_stroke_gradient_start_color**|**描边渐变起始颜色**
**XButtonCustom_stated_stroke_gradient_end_color**|**描边渐变结束颜色**
**XButtonCustom_stated_stroke_gradient_middle_color**|**描边渐变中间颜色**
**XButtonCustom_stated_stroke_gradient_offset_x**|**描边渐变Linear shader 起始x (参见: LinearGradient 参数, 默认为0)**
**XButtonCustom_stated_stroke_gradient_offset_x1**|**描边渐变Linear shader 截止x1 (参见: LinearGradient 参数, 默认为视图宽度)**
**XButtonCustom_stated_stroke_gradient_offset_y**|**描边渐变Linear shader 起始y (参见: LinearGradient 参数, 默认为0 )**
**XButtonCustom_stated_stroke_gradient_offset_y1**|**描边渐变Linear shader 截止y1 (参见: LinearGradient 参数, 默认为0 )**
**XButtonCustom_drawable**|**正常情况下显示的drawable**
**XButtonCustom_stated_drawable**|**在特定状态下显示的drawable, 如: selector**
**XViewTemplate_shaped**|**是否形状背景，可以支持切角、内切角下的渐变填充、描边等**
**XViewTemplate_shaped_topLeftCornerType**|**左上角切角类型，支持圆角、切角（corner or cut）**
**XViewTemplate_shaped_topRightCornerType**|**右上角切角类型，支持圆角、切角（corner or cut）**
**XViewTemplate_shaped_bottomLeftCornerType**|**左下角切角类型，支持圆角、切角（corner or cut）**
**XViewTemplate_shaped_bottomRightCornerType**|**右上角切角类型，支持圆角、切角（corner or cut）**
**XViewTemplate_shaped_topLeftSize**|**左上角切角大小**
**XViewTemplate_shaped_bottomLeftSize**|**左下角切角大小**
**XViewTemplate_shaped_topRightSize**|**右上角切角大小**
**XViewTemplate_shaped_bottomRightSize**|**右下角切角大小**
**XViewTemplate_shaped_leftEdgeType**|**左边切角类型，目前仅支持三角**
**XViewTemplate_shaped_rightEdgeType**|**右边切角类型，目前仅支持三角**
**XViewTemplate_shaped_topEdgeType**|**上边切角类型，目前仅支持三角**
**XViewTemplate_shaped_bottomEdgeType**|**下边切角类型，目前仅支持三角**
**XViewTemplate_shaped_topEdgeSize**|**上边内切角大小**
**XViewTemplate_shaped_bottomEdgeSize**|**下边内切角大小**
**XViewTemplate_shaped_leftEdgeSize**|**左边内切角大小**
**XViewTemplate_shaped_rightEdgeSize**|**右边内切角大小**

> 上述的字段在代码中的修改方式，支持的修改的字段在`IDrawableEditTransition` 接口中定义

```
    // 获取IDrawableEditTransition
    XWidget.getDrawableEditTransition(v)
            ?.beginNormalTransition
            ?.setCorner(10)
            ?.commit()
```

## XWidget框架介绍

#### 1.开发过程中Drawable xml文件膨胀问题
    开发过程中，为了实现UX设计的好看的效果，通常避免不了去写很多的Drawable xml文件。比如: 圆角Shape， 渐变，Selector等等。
由于UX效果复杂多变，UI元素的尺寸形状必定会存在很多差异，所以就要编写很多Drawable xml文件去实现效果。加之多个开发人员并行开发过程中，如果没有较强的文件命名
规定约束，其他人也会加入相同的Drawable xml文件。导致越来越多的xml文件加入到项目中，不仅会增加应用包体积，也不利于后续复用。

#### 2.通常解决方案
    1.通常的解决方案是自定义View代替，用代码去绘制相应效果。但是不能保证代码实现的效率，且常用的View较多，挨个实现自定义View较大。
    2.约定文件命名，保证同样的Drawable xml只能存在一份，这样只能避免冗余的Drawable xml 存在，并不能减少必要的Drawable文件的生成。况且，没有程序去约束，光靠人
    去遵循约定是不可靠的。

#### 3.XWidget解决方案

`XWidget`提供丰富的 "自定View"，且基本包含常用的Drawable 属性。虽然'XWidget'也是自定义View，但是其只是作为一个载体，真正绘制是和我们写的Drawable xml
原理一样，用自定义属性构造出对应的`GradientDrawable` or `StateListDrawable`对象，设置到View中。这样的实现方案既保证了绘制时的性能，又能够在布局中高度定制化，
实现复杂的UI效果。


