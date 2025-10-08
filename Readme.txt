INTRODUCTION
    Before we dive deep into Multithreading and Concurrency, these are the basic things to know to better understand how systems work.

    PROGRAMS

        The executable file created after writing the code is known as a program.
        In Java, after building an application, we create a WAR or JAR file — this is the program.
        In C, C++, and Rust, the compiled file creates a binary bit code that can be executed on any system, even if the language itself isn’t installed.
        This packaged piece of code is known as a program.

    PROCESS

        Simplest definition: A program in execution.
        Example:
        Let’s take Windows OS. Suppose you open Google Chrome to browse some data.
        What actually happens in the background?
        The operating system creates a process that has its own memory space.
        This memory is completely isolated from other processes.
        Each process has a PCB (Process Control Block) — a data structure that contains details about that process, including its PID (Process ID).
        You can imagine the PCB as a local system table having all process data, where PID acts as the primary key.
        Within a process, there are smaller units of execution called threads.
        These threads share the same memory space as the parent process, though each has its own call stack and program counter.

    CPU

        The CPU (Central Processing Unit) is the brain behind all internal system logic.
        The efficiency of an operating system depends on how well it utilizes the CPU to provide smooth and responsive performance.
        The CPU executes all the process instructions.
        Which process gets executed first or next is determined by CPU scheduling algorithms, not by the user.

    CONTEXT SWITCHING

        The ability of the CPU to switch between processes — even when one process hasn’t completed — is known as context switching.
        Example:
        Suppose you have Chrome downloading a file while you are writing in MS Word.
        Although both appear to run simultaneously, the CPU actually switches back and forth between them extremely fast.
        This switching creates the illusion of parallel execution.

    SYNCHRONIZATION

        When multiple processes or threads share resources (like data), synchronization mechanisms are used to avoid conflicts and maintain data consistency.
        EXAMPLE – GOOGLE DOCS: COLLABORATIVE EDITING
        Google Docs shows concurrency in action. When multiple users edit a document simultaneously, each user’s actions are handled by separate threads.
        • Thread per user: Each user’s editing actions are processed by an individual thread.
        • Conflict resolution: Threads synchronize to merge edits and avoid conflicts.
        • Auto-suggest or spell-check: Separate threads handle background features like auto-completion.
        • UI thread: Manages the continuous updates on the user interface.


DEEP DIVE

    PROCESS

        A process is a program in execution.
        It starts when a program begins running, executes code in the CPU, and terminates once execution completes.
        The memory allocated to a process is freed upon termination.

    Process Lifecycle:

        Created when program starts execution
        Execution continues until process completes or is terminated
        Termination releases memory and resources back to the OS
        Each process runs in its own user space, isolated from others.

    THREADS

        Threads are mini-processes inside a process.
        They share the same heap memory but maintain their own call stacks and program counters.

    Common challenges with threads:

        • Since threads share memory, conflicts may arise when multiple threads access the same data simultaneously.
        • Deadlocks can occur when threads wait for each other indefinitely for shared resources.

    Example:

        In Google Docs, if two users modify the same word at exactly the same microsecond, this creates a conflict that must be handled carefully through synchronization.

    CONCURRENT EXECUTION VS PARALLEL EXECUTION

        Before defining both, two important terms:
        • Making progress – Executing lines of code; not necessarily continuous.
        • At an instant – A single CPU time unit.

    Concurrent Execution:

        When multiple programs run on a system and the CPU switches rapidly between them to create the appearance of simultaneous progress.
        This is called concurrency.

    Parallel Execution:

        When multiple processes are actually making progress at the same instant of time.
        True parallelism requires multiple CPU cores.
        Example:
        A single-core CPU can only perform concurrent execution (through context switching).
        A multi-core CPU can perform true parallel execution, since each core can execute a thread independently.

    REAL-LIFE ANALOGY

        Imagine a bank with two separate queues — one for men and one for women.

    Single-core analogy:

        If there is only one cashier, he alternates between serving one person from each queue.
        This is concurrency or context switching.

    Multi-core analogy:

        If there are two cashiers, each serving one queue independently, this is parallelism.

MULTI-THREADING IN JAVA :
   
    In the Java, multithreading is driven by the core concept of a Thread. There are two ways to create Threads in Java.

    Thread Class: 
        Java provides the Thread class, which serves as the foundation for creating and managing threads.

    Runnable Interface: 
        The Runnable interface is often implemented to define the code that a thread will execute.
        Lets write some logic that runs in a parallel thread by using the Thread framework. In the below code example we are creating two threads and running them concurrently.

    Thread Execution Handling : 
        What ever code write in the main function is handled by main thread in synchronous fashion. If we want to let the JVM Know to handle which task needs to be done asynchronously 
        then we need to create a separate thread for that task. 
        Which thread to handle when is handled by JVM : (Java Virtual Machine). Java provides many interfaces and tools to efficiently handle thread execution, to avoid boiler plate code of handling of thread
        execution. 

Lets take an example to understand how we can create separate thread to handle separate tasks. 
Refer to NUMBER PRINTING DIRECTORY 




EXECUTORS AND CALLABLES IN JAVA : 

    Imagine you have a computer program that needs to do several tasks at the same time. For example, your program might need to download files, process data, and update a user interface simultaneously. 
    In the traditional way of programming, you might use threads to handle these tasks. However, managing threads manually can be complex and error-prone. Java ExecutorService implementations let you stay focused on tasks that need to be run, rather than thread creation and management.
    Think of a chef in a kitchen as your program. The chef has multiple tasks like chopping vegetables, cooking pasta, and baking a cake. Instead of the chef doing each task one by one, the chef hires sous-chefs (threads) to help. The chef (Executor Framework) can assign tasks to sous-chefs efficiently, 
    ensuring that multiple tasks are happening simultaneously, and the kitchen operates smoothly.
    In Java, the Executor Framework provides a convenient way to implement this idea in your code, making it more readable, maintainable, and efficient. It simplifies the process of managing tasks concurrently, so you can focus on solving the problems your program is designed to address.

    We have this executors available in Java.util.concurrent package that can effectively handle thread lifecycles. 
    Depending upon our need we can use different threadpools available in executor 

    Creating threads, destroying threads, and then creating them again can be expensive.
    A thread pool mitigates the cost, by keeping a set of threads around, in a pool, for current and future work.
    Threads, once they complete one task, can then be reassigned to another task, without the expense of destroying that thread and creating a new one.
    A thread pool consists of three components.

    Worker Threads are available in a pool to execute tasks. They're pre-created and kept alive, throughout the lifetime of the application.

    Submitted Tasks are placed in a First-In First-Out queue. Threads pop tasks from the queue, and execute them, so they're executed in the order they're submitted.

    The Thread Pool Manager allocates tasks to threads, and ensures proper thread synchronization.

TYPES OF THREADPOOLS AVAILABLE : 

    1. FixedThreadPool 

        This fixes the number of threads that can be created to effectively manage the cost of creating and destroying multiple threads. The threads are created when the threadpool is assigned 
        and the tasks are reassigned to the idle threads. 
        If all threads are busy, tasks wait in FIFO queue untill the one of the thread becomes available

    2. CachedThreadPool 

        This threadpool dynamically adjust the number of threads based on the demand.
        Creates new threads as needed, but reuses idle threads if available.
        Threads that are idle for a certain duration are terminated.
        Suitable for handling a large number of short-lived tasks.
    
    3. SingLeThreadExecutor 

        This thread pool has a single thread executing the tasks. All the tasks wait in the FIFO queue to complete its execution. 
    
    4. ScheduledThreadPool 

        Mostly similar to fixedThreadPool but has added dlexibilituy of delaying the execution of tasks. 
        Suitable for periodic tasks 
    
    5. WorkStealingPool 

        Efficient to use in multi core systems. 
        Adjusts the threads to available processors 
        Each processor has its own Thread queue 
        Suitable to use in parallel tasks. 

    Advantages :

        1. Simplifies Task Execution

        The Executor Framework makes it easier to execute tasks concurrently. It abstracts away the low-level details of managing threads, so you don't have to worry about creating and controlling them yourself.

        2. Efficient Resource Utilization:

        When you have many tasks to perform, creating a new thread for each task can be inefficient. The Executor Framework provides a pool of threads that can be reused for multiple tasks. This reuse of threads reduces the overhead of creating and destroying threads for every task.

        3. Better Control and Flexibility

        With the Executor Framework, you can control how many tasks can run simultaneously, manage the lifecycle of threads, and specify different policies for task execution. This level of control is important for optimizing the performance of your program.

        4. Enhanced Scalability

        When your program needs to handle more tasks, the Executor Framework makes it easier to scale. You can adjust the size of the thread pool to accommodate more tasks without rewriting a lot of code.

        5. Task Scheduling

        The framework allows you to schedule tasks to run at specific times or after certain intervals. This is useful for scenarios where you want to automate repetitive tasks or execute tasks at specific points in time.

    Summary

    Managing threads manually can be complex and error-prone.
    It can lead to complex issues like resource contention, thread creation overhead, and scalability challenges.
    For these reasons, you'll want to use an ExecutorService, even when working with a single thread.

CALLABLES AND FUTURES:

    Runnables do not return a result. If we want to execute a task that returns a result, we can use the Callable interface. The Callable interface is a functional interface that has only one method:

    The call method returns a result of type V. The call method can throw an exception. The Callable interface is used to execute tasks that return a result. For instance we can use the Callable interface to execute a task that returns the sum of two numbers:


    Refer Executor Service examples.