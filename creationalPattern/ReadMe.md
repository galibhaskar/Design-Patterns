# Creation Design Pattern:
- Focuses on object creation logic.
- Separates the object creation logic from business logic.

---

# Types:
- ### Singleton
    - **Usecase:** When we need global or shared resources across the application.
    - **Methods:**
        - Eager loading(thread-safe but object will be created when the class is loaded)
        - Lazy loading(not thread-safe)

    - **Techniques to make lazy loading thread-safe:**
        1. Synchronized keyword(synchronized method)
        2. Double checking with volatile keyword(double check with synchronized block)
        3. Bill Pugh Singleton(eager loading with inner class)

    - **Code:** [Checkout Here](./Singleton.java)

- ### Factory
    - **Usecase:** Object creation based on the parameter
    - **Methods:**
        1. create interface - Product
        2. create concrete classes using interface - ConcreteProduct
        3. create a factory(using if-else or switch) - ProductFactory
    - **Code:** [Checkout Here](./Factory.java)

- ### Builder
    - **Usecase:** Multiple optional parameters requires Constructor Telescoping(overloading multiple constructors with different combinations of params) and *object immutability* is required.
    - **Methods:**
        1. create a main class with final attributes - Product
        2. create a builder class inside the main class with non-final product attributes - ProdBuilder
        3. Add methods for optional params(withAttr1(), withAttr2(),...-FLUENT API style) that return ProdBuilder instance.
        4. Finally, write the build() inside builder class that creates Product instance and returns the Product instance.
    - **Code:** [Checkout Here](./Builder.java)

- ### Abstract Factory
    - **Usecase:** Factory of Factories
    - **Methods:**
        1. Create factories using Factory pattern
        2. Combine those factories using parent Factory using an interface or abstract classes.
    - **Code:** [Checkout Here](./AbstractFactory.java)

- ### Prototype
    - **Usecase:** Creating complex objects with almost same data(with minimal changes).
    - **Methods:**
        1. Create Cloneable interface or Cloneable class that overrides clone()
        2. Create a central registry that creates and returns the cloned instance - PREFERRED.
    - **Code:** [Checkout Here](./Prototype.java)
