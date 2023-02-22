#业务服务 提供api;
####实现前端传递数值 后端转换枚举
1. 实现了枚举类转化工厂：前端传来string，后端接收映射为枚举
2. 定义一个通用的枚举接口 IBaseEnum 统一使用getValue 取枚举值。
3. 定义了一个工厂bean `public class EnumConvertFactory implements ConverterFactory<String, IBaseEnum>`
4. 重写ConverterFactory 里的`public <T extends IBaseEnum> Converter<String, T> getConverter(Class<T> targetType)` 方法
5. 然后把工厂注入到webconfig中 
`public void addFormatters(FormatterRegistry registry) {
registry.addConverterFactory(enumConvertFactory);
}`
6. 此时 后端已经能用枚举去接收前端传来的数值了，save方法也通过了，保存的是枚举对应的数值 但是前端需要指定枚举的描述，也就是description字段作为枚举的显示
7. 这时用到了 @JsonValue 注解 该注解可以将枚举序列化时指定序列话的字段，将json传递给前端时，只会把 加了@JsonValue注解的字段序列化