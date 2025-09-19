# Painter App

In this exercise, we are practicing:

- **Event handling**: using button and mouse events to build an interactive application.
- **Refactoring**: changing the design of existing code without changing its functionality.
- **Iterative development**: building up complex software in small, testable steps, adjusting the structure of the code as we go.

In this exercise, you build up a simple paint program.

<img src="doc/images/finished-project.png" width="512">


## Step 0: Understand your starting point and create a class diagram

You start with the following classes:

- `PainterApp`: The main class of the application.
- `BrushOptions`: A model object that contains parameters that control a brush.
- `PaintSettingsView`: A UI component that allows a user to choose different `BrushOptions`.
- `PaintUtils`: Helper functions for creating an airbrush / spray paint effect.

`PainterApp` is the only one of the existing classes you will modify in this exercise.

You should understand the **public methods** and the **general purpose** of the other classes, but you do **not** need to understand how they are implemented. (You are of course welcome to read through them! But don’t get bogged down in the details. Let abstraction do its job. The point of abstraction is that you can add to or change existing code without needing to understand _everything_ about it!)

Try running the code. You should see a user interface for choosing a color and brush size. You should also see a single fuzzy blue dot on the screen — but it just sits there and doesn’t do anything.

### Diagram the class relationships

After becoming acquainted with the starter code in the assignment, think further about the class structure and relationships. Please draw a diagram showing the four classes listed above (`PainterApp`, `BrushOptions`, `PaintSettingsView`, and `PaintUtils`) with arrows between them showing the following three relationships (and _only_ those relationships):

- **has-a**: one class holds a reference to another as part of its state
- **is-a**: one class is a subtype of another
- **creates**: one class creates a new instance of another

Label every arrow in your diagram with the kind of relationship it denotes. (One arrow may have multiple labels.)

Make sure your arrows point in the correct direction.

When you are done with your diagram, please upload it to the Moodle submission dropbox for the Painter Homework assignment. You can submit a photo of a paper sketch, or a digital version created with a drawing program or a slide generator. Just make sure it’s legible enough for us to read it!

Please use this name format for your file: `yourlastname-hw3-sectionXX-diagram-v1.file-extension`

:warning: Keep your initial structure in mind. Towards the end of the assignment, you will be asked to create another diagram to represent the changes your class structure has undergone!

## Step 1: Draw some fuzzy dots

We have given you some starting code for the paint program's user interface. It’s missing one crucial thing: it doesn’t paint! We have also given you some code to create fuzzy dots. By creating many of these fuzzy dots and placing them on the screen as the user drags the mouse, you will create a spray paint effect.

Open up `PainterApp` and look at the `paint` method. That method will be called repeatedly with different locations as the mouse moves. (That part already works.) Your job: make it create **one** fuzzy dot every time it is called, and place the dot on the canvas at `location`.

Here is an outline of the code you need to write to replace the TODO:

- Call the `createFuzzyDot` method in `PaintUtils` to create one dot:
  - Use the color from `brushOptions`
  - Use the radius from `brushOptions`
  - Use an alpha of `0.2f` (the `f` means the constant is a float instead of a double)
- Set the center of the dot to `location`
- Add the dot to the canvas

Here are some hints to help you get started:

<details>
  <summary>🔹
    How do I call <code>createFuzzyDot</code>?
  </summary>

  > It is a static method. How do you call a static method of another class? (The test classes in both HW0 and HW1 have examples of this.)
</details>

<details>
  <summary>🔹
    How do I know what parameters <code>createFuzzyDot</code> takes, and what they mean?
  </summary>

  > Open up `PaintUtils.java` and read the documentation!
</details>

<details>
  <summary>🔹
    I see that <code>location</code> is a <code>Point</code> object, but I don’t know how to use that. How do I set the center of the dot to <code>location</code>?
  </summary>

  > What is the return type of `createFuzzyDot`? That class has a method for setting the center that accepts a `Point`.
</details>

Don’t forget to delete the TODO comment!

Test well, make sure it works properly, then ✅ commit your work.


## Interlude: Let’s plan!

Your main goal in this exercise is to support multiple brushes. You currently have a spray paint effect; you will add two more.

The code is not yet ready for multiple brushes. You will get ready for that with the following two steps. (Not yet! We are looking ahead! This is a plan, not instructions!)

1. Extract a separate `Brush` class from `PainterApp` that is responsible for nothing but placing the fuzzy dots.
2. Convert that to a `SprayPaint` class that implements a `Brush` interface, and make `PainterApp` depend _only_ on the interface.


## Step 2: Extract `Brush` class

Let’s do just step 1. Keep in mind that for now, `Brush` will just be a class — not an interface!

Create a new `Brush` class with **no instance variables**, and just one method named `apply`:

```java
public class Brush {
    public void apply(...) {
        ...
    }
}
```

Add an instance variable called `currentBrush` to `PainterApp`. Initialize it with a new `Brush` object.

Now move some — but not all! — of the contents of the `PainterApp.paint()` method into `Brush.apply()`. Which parts should move? What parameters should `apply()` take? You need to figure this out. This is a lot like the Cell Absorption exercise. Guidelines:

- Any specifics about what the brush _does_ should be in the `Brush` class. The main app will ask the brush to apply itself to the canvas without knowing anything specific about how the brush works.
- Any specifics about what UI components are on the screen should be in `PainterApp`. The brush should know it is applying itself to some canvas, but it should not know anything else about how the UI is organized.
- Any information the brush needs to apply itself to the canvas, the `apply()` method should accept as a parameter. Again, `Brush` should have no instance variables.

Think about how to extract the class, and ask for tips if you are unsure.

Once this refactoring is done, you should still be able to paint exactly as before.

Test well, make sure it works properly, then ✅ commit your work. Be sure to commit the newly created class!


## Step 3: Extract `Brush` interface

Now make `Brush` an interface with the `apply()` method, and `SprayPaint` the one class that implements it.

In `PainterApp`, the type of `currentBrush` should still be `Brush`, but now you initialize it with a new `SprayPaint` object. What is the syntax for that? Think, try it, then check your work:

<details>
  <summary>🔹
    Expand this section to check your answer
  </summary>

  ```java
  private Brush currentBrush = new SprayPaint();
  ```
</details>

Once this refactoring is done, everything should _still_ behave exactly as before. But now we are ready for multiple kinds of brushes.

Test well, make sure it works properly, then ✅ commit your work.


## Step 4: Add a new brush

You will create a new implementation of `Brush` that draws thin unfilled circles on the screen. Be sure to respect the brush color and brush radius.

You have all the ingredients you need to figure out how to do this. If you’d like a hint, or want to check your work, here is a sketch of the new class in pseudocode:

<details>
  <summary>🔹
    Expand this section for pseudocode
  </summary>

  ```
  declaration for the `CirclesBrush` class, which implements the `Brush` interface:
    declaration for the `apply` method:
        get radius from `brushOptions`
        make a new circle with diameter of radius * 2
        set the circle's stroke color from `brushOptions`
        set the circle's stroke width to some fraction less than 1 (we want thin circles!)
        set the circle's center to the location passed as a parameter
        add the circle to the canvas
  ```
</details>

Try making `PainterApp` use your new brush instead of the old one. You should be able to do this by doing nothing except changing `new SprayPaint()` to `new CirclesBrush()` (or whatever you called the new brush class).

You should now see circles instead of the spray paint effect.

Test well, make sure it works properly, then ✅ commit your work.


## Step 5: Add the ability to switch brushes

In `PainterApp`, create a new method `addBrushButton(Brush brush, double y)` that does the following:

- Create a `Button` with the title “Change Brush”. (Make sure you have the`Button` from `kilt-graphics`, and not a different `Button` class.)
- Position it at `10, y`.
- Add it to the canvas.
- Make it so that when the user clicks it, it sets `currentBrush` to this brush. We haven’t yet covered in class how to make buttons clickable, so here is the Java syntax you need:

  ```java
  button.onClick(() -> {
    // Put your code here to change `currentBrush` to the button’s brush
  });
  ```

Add a new instance variable called `availableBrushes` that is a `List` of `Brush` objects. Put one instance of each of your two brushes in the list.

In the constructor, loop over `availableBrushes` and call `addBrushButton()` for each one. (Be sure to pass different `y` coordinates so they aren’t all on top of each other.)

Run your code. You should see two “Change Brush” buttons. If you click one and then paint, you should see spray painting; if you click the other and then paint, you should see circles. Polymorphism unleashed!

Test well, make sure it works properly, then ✅ commit your work.


## Step 6: Add an eraser

Make a new implementation of `Brush` called `Eraser`.

`Eraser` should do the following:

- Call `canvas.getElementAt()` to get the graphics object at the given position.
- If that method did not return null, call `canvas.remove()` with the object you found.

This should take just a little bit of code.

Add `Eraser` to the end of `availableBrushes`, and try it out! You should be able to paint something, then switch to the eraser by clicking the appropriate button and erase parts of what you painted. Yay!

But wait…. Try dragging the eraser over the “Change Brush” buttons on the left of the screen. Oops.

That’s OK. This is progress! ✅ Commit your work, and then you can fix the bug.


## Step 7: Fix the eraser

The problem is that when you call `getElementAt()`, you remove _any_ element you find — including the buttons!

> Aside: Note that the eraser does _not_ remove the color and brush size UI elements. Why not? They are not directly inside the canvas; they are all inside a `GraphicsGroup` inside the canvas — a child of a child of the canvas. The `getElementAt()` method will look for children of children inside groups, but the `remove()` method only allow you to remove immediate children. So the canvas says, “Sorry, I don’t have that element!” and nothing happens.

One solution would be to try to look at the matched element and figure out whether it is part of the painting or part of the controls. But that would be a *brittle change*: we want to be able to add new kinds of controls and new kinds of brushes, without constantly having to worry about making sure the eraser correctly identifies which is which.

A better solution is to put the entire painting inside a `GraphicsGroup`. That group will contain _only_ the painting and not the whole UI, so we can safely remove _anything_ from it.

In `PainterApp`, add a new `GraphicsGroup` instance variable called `paintLayer`. In the constructor, initialize it and add it to the canvas:

```java
paintLayer = new GraphicsGroup();
canvas.add(paintLayer);
```

Now find the call to `currentBrush.apply()`, and change the `canvas` parameter to `paintLayer`.

Oops! This breaks! Why? Because `CanvasWindow` is not a `GraphicsGroup`. That means you need to change the `Brush` interface — and all the classes that implement it — so they take a `GraphicsGroup` instead of a canvas.

> Aside: This is a bunch of work, isn’t it? Wouldn’t it be great if we’d done it this way from the beginning? You may be tempted to think, “If only we’d foreseen this bug! If only we’d planned more!” And it’s true, that would have been nice, and planning is good. But foreseeing all problems in software is a fool’s errand.
>
> It is more important to have a clear sense of purpose and a constant feedback loop than it is to plan every technical detail perfectly from the beginning. In fact, overanticipating possible future needs that never materialize is the undoing of many a software project!
>
> Focus on sensible, achievable steps that work toward a clear goal. Test along the way, and be ready to adjust. Perfect foresight is not possible, but flexibility and common sense are.

Once you have made that change, all the brushes will work entirely within `paintLayer`. You should still be able to paint and erase as before, but the eraser should no longer remove any buttons. (And as a side bonus, you can no longer paint over the color preview square in the upper left! Did you notice that bug? It’s fixed now too.)

Test well, make sure it works properly, then ✅ commit your work.


## Step 8: Give the brushes names

You’re almost there! In this last step, you get a good feel for what iterative development really feels like in practice.

It’s annoying that all the buttons have the same name, isn’t it? Let’s give the different buttons different names.

Add a `getName()` method to the `Brush` interface.

Make each class that implements `Brush` implement that method by returning a different user-friendly string. (By “user friendly,” I mean that for example you should return `"Spray Paint"` with a space in it, so it sounds like something that is meaningful to a user/artist, not meaningful to a programmer.)

When you call the `Button` constructor, instead of passing `"Change Brush"`, pass the brush’s name.

Run the program. You should now have a UI with three buttons with different names.

Test well, make sure it works properly, then ✅ commit your work.


## Step 9: Be creative! Make additional brushes! 

You know you want to, right? Of course you do. Be inventive! Have fun!

Rules for full credit:

- You must create at least one new brush. (We’d be delighted if you create more!)
- Your new creation cannot just be a minor modification of an existing brush. For example, merely making a `SquareBrush` that is just like `CircleBursh` except with a rectangle will **not** receive full credit.

Not sure where to start, or what’s enough? Here are a few ideas:

- A brush that stamps an image on the canvas
- A brush that draws a cool shape of your own invention
- A brush that draws a continuously rotating shape
- A brush that draws spikes coming out from the mouse in random directions
- A brush that makes a splatter pattern by drawing dots of random size offset from the mouse’s location by a random distance

Try one of those ideas, or invent your own!

As usual, test well, make sure it works properly, and **enjoy your excellent work** by creating some MORE AWESOME art! Then ✅ commit and push.


## Step 10: Revisiting your class diagram!

Remember wayyyyyy at the beginning of this assignment, you were asked to create an initial diagram of the class structure and its relationships? Well, your final task (woohoo!) will be to draw an updated diagram with the new class structure and relationships.

Draw a diagram in the same style as the first one, now showing _all_ of the classes and interfaces in the `painter` package.

Again, upload your second diagram via Moodle submission dropbox for the Painter Homework assignment. 

To distinguish your prior diagram and your updated one, use a similar naming convention (where now we use v2 for version 2): `yourlastname-hw3-sectionXX-diagram-v2.file-extension`

Now, you should have **two** diagram file submissions on Moodle, along with your code pushed to GitHub. If you have done all these things, CONGRATULATIONS!
