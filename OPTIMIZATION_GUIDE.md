# XWidget 代码优化指南

## 优化概述

本次优化主要针对代码设置方式和使用流程进行了改进，使代码更加优雅、简洁、易用。

## 主要优化内容

### 1. 简化初始化流程

#### 优化前
```kotlin
@XWidgetView("com.example.viewdemo")
class XTextViewCustom(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        XWidget.inject(this, attrs, null /*自定义解析器*/)
    }
}
```

#### 优化后
```kotlin
@XWidgetView  // 包名可省略，自动检测
class XTextViewCustom(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        injectXWidget(attrs)  // 使用扩展函数，更简洁
    }
}
```

**改进点：**
- 新增 `XWidgetExtensions.kt` 提供扩展函数 `injectXWidget()`
- 注解包名参数变为可选，支持自动检测
- 代码更简洁，减少样板代码

### 2. 注解自动检测包名

#### 优化前
```kotlin
@XWidgetView("com.example.viewdemo")  // 必须手动指定包名
```

#### 优化后
```kotlin
@XWidgetView  // 自动从View所在包名推断
// 或
@XWidgetView("com.example.viewdemo")  // 仍支持手动指定
```

**改进点：**
- 注解参数 `resourcePackageName` 变为可选（默认空字符串）
- 处理器自动从View的包名推断资源包名
- 减少配置错误，提高开发效率

### 3. 配置化设计 - AreaSelectView

#### 优化前
```kotlin
// 配置硬编码在View内部
private val paint = Paint().run {
    color = Color.parseColor("#FF0000")  // 硬编码
    strokeWidth = 6F
    // ...
}
```

#### 优化后
```kotlin
// 使用配置类
val config = AreaSelectViewConfig.Builder()
    .strokeColor(Color.BLUE)
    .strokeWidth(8f)
    .maskColor(Color.parseColor("#88000000"))
    .touchBoxSize(40f)
    .build()

areaSelectView.setConfig(config)
```

**改进点：**
- 新增 `AreaSelectViewConfig` 配置类，支持Builder模式
- 配置与View分离，便于复用和管理
- 支持运行时动态修改配置

### 4. 配置化设计 - PositionSelectView

#### 优化前
```kotlin
// 配置硬编码
private val rectBorderColor = Color.parseColor("#FF0000")
private val circleR = 20f
// ...
```

#### 优化后
```kotlin
// 使用配置类
val config = PositionSelectViewConfig.Builder()
    .rectBorderColor(Color.RED)
    .circleRadius(25f)
    .centerCircleColor(Color.GREEN)
    .build()

positionSelectView.setConfig(config)
```

**改进点：**
- 新增 `PositionSelectViewConfig` 配置类
- 统一配置接口，易于扩展
- 支持链式调用，代码更优雅

### 5. 统一配置管理

新增 `ViewConfigManager` 统一管理配置：

```kotlin
// 使用默认配置
ViewConfigManager.applyDefaultConfig(areaSelectView)

// 使用Builder创建自定义配置
val customConfig = ViewConfigManager.areaSelectConfig()
    .strokeColor(Color.BLUE)
    .strokeWidth(8f)
    .build()
```

**改进点：**
- 集中管理默认配置
- 提供便捷的配置创建方法
- 便于统一修改和维护

### 6. 代码清理

- 清理了 `MainActivity` 中的注释代码
- 添加了清晰的使用示例
- 改进了代码注释和文档

## 使用示例

### 自定义View使用XWidget

```kotlin
import com.scott.xwidget.annotation.XWidgetView
import com.scott.xwidget.injectXWidget

@XWidgetView  // 包名自动检测
class MyCustomView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    init {
        injectXWidget(attrs)  // 一行代码完成初始化
    }
}
```

### 配置AreaSelectView

```kotlin
// 方式1：使用默认配置
ViewConfigManager.applyDefaultConfig(areaSelectView)

// 方式2：使用Builder自定义
val config = ViewConfigManager.areaSelectConfig()
    .strokeColor(Color.BLUE)
    .strokeWidth(8f)
    .maskColor(Color.parseColor("#88000000"))
    .touchBoxSize(40f)
    .build()
areaSelectView.setConfig(config)
```

### 配置PositionSelectView

```kotlin
val config = ViewConfigManager.positionSelectConfig()
    .rectBorderColor(Color.RED)
    .rectBorderWidth(6f)
    .circleRadius(25f)
    .centerCircleColor(Color.GREEN)
    .offsetCircleColor(Color.BLUE)
    .build()
positionSelectView.setConfig(config)
```

## 文件变更清单

### 新增文件
- `xwidget/src/main/java/com/scott/xwidget/XWidgetExtensions.kt` - 扩展函数
- `app/src/main/java/com/example/viewdemo/AreaSelectViewConfig.kt` - AreaSelectView配置
- `app/src/main/java/com/example/viewdemo/PositionSelectViewConfig.kt` - PositionSelectView配置
- `app/src/main/java/com/example/viewdemo/ViewConfigManager.kt` - 配置管理器

### 修改文件
- `xwidget-annotation/src/main/java/com/scott/xwidget/annotation/XWidgetView.kt` - 注解优化
- `xwidget-processor/src/main/java/com/scott/xwidget/processor/XWidgetProcessor.java` - 处理器优化
- `app/src/main/java/com/example/viewdemo/AreaSelectView.kt` - 支持配置
- `app/src/main/java/com/example/viewdemo/PositionSelectView.kt` - 支持配置
- `app/src/main/java/com/example/viewdemo/XTextViewCustom.kt` - 使用扩展函数
- `app/src/main/java/com/example/viewdemo/XButtonCustom.kt` - 使用扩展函数
- `app/src/main/java/com/example/viewdemo/MainActivity.kt` - 代码清理和示例

## 优势总结

1. **更简洁** - 减少样板代码，一行代码完成初始化
2. **更灵活** - 配置与View分离，支持动态修改
3. **更优雅** - Builder模式，链式调用
4. **更易用** - 自动检测包名，减少配置错误
5. **更易维护** - 统一配置管理，便于扩展

## 向后兼容性

所有优化都保持向后兼容：
- 旧的注解使用方式仍然有效
- 旧的初始化方式仍然有效
- 配置类提供默认值，不影响现有代码
