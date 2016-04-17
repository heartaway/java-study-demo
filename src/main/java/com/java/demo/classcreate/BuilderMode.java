package com.java.demo.classcreate;

/**
 * <p>构建器模式代替JavaBeans作为函数</p>
 * User: 心远
 * Date: 14/12/16
 * Time: 上午1:06
 * 使用场景：当类的构造函数参数超过4个的时候
 * 优势：可以通过代理类的构造器来约定那些参数是必填的，那些是可选的；其次可以在代理层对参数做一次逻辑校验；添加参数时非常方便；
 * 劣势：通过代理类还需要做一次对象的拷贝，在一定程度上影响了性能；在代码编写上也繁琐了一些；
 */
public class BuilderMode {

    private Long userId;
    private String name;
    private String password;
    private String sex;
    private String email;
    private String phone;

    public static class Builder implements com.java.demo.classcreate.Builder<BuilderMode> {
        private Long userId;
        private String name;
        private String password;
        private String sex;
        private String email;
        private String phone;

        public Builder(Long userId, String name, String password) {
            this.userId = userId;
            this.name = name;
            this.password = password;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        @Override
        public BuilderMode build() {
            return new BuilderMode(this);
        }
    }

    /**
     * 构造器定义为私有，只能通过builder来创建实例
     * @param builder
     */
    private BuilderMode(Builder builder) {
        userId = builder.userId;
        name = builder.name;
        password = builder.password;
        sex = builder.sex;
        email = builder.email;
        phone = builder.phone;
    }


    public static void main(String[] args) {
        BuilderMode builderMode = new BuilderMode.Builder(1L,"","").setPhone("").build();
    }
}
