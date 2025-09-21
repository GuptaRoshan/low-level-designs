## Fixed Window Counter

The Fixed Window Counter is one of the most straightforward rate-limiting algorithms. Imagine it as a simple clock that tracks requests within a specific, unmoving time block.

Here's how it works: you define a time window, such as one minute. The algorithm uses a single counter for all requests that arrive within that minute. When a request comes in, the counter increments. If the counter is below your predefined limit, the request is allowed. If the counter reaches the limit, all subsequent requests for that minute are denied. When the clock hits the start of the next minute, the counter is reset to zero, and a new window begins.

The algorithm's simplicity also creates its biggest weakness: the "boundary problem." This occurs when a user makes requests right at the end of one window and the beginning of the next.

**Walkthrough Example**

Let's use a limit of 3 requests and a window size of 60 seconds.

**Window 1: 00:00 to 00:59**

- At 00:05: Request 1 arrives. The counter is 0, which is < 3. The request is Allowed. The counter is now 1.

- At 00:15: Request 2 arrives. The counter is 1, which is < 3. The request is Allowed. The counter is now 2.

- At 00:25: Request 3 arrives. The counter is 2, which is < 3. The request is Allowed. The counter is now 3.

- At 00:30: Request 4 arrives. The counter is 3, which is == 3. The request is Denied.

**The Boundary Problem**

- At 00:59: A user sends 3 requests. The counter is 0, so all 3 requests are Allowed. The counter is now 3.

- At 01:00: The clock ticks to the next window. The counter is immediately reset to 0.

- At 01:01: The user sends another 3 requests. The counter is 0, so all 3 requests are Allowed. The counter is now 3.

In this scenario, the user sent 6 requests in just a little over a minute, completely bypassing the intended limit of 3. This sudden spike can still overload a server.



## Sliding Window Log

The **Sliding Window Log** is the most accurate rate-limiting algorithm. Instead of using a simple counter, it keeps a log of the timestamp for every single request that arrives. When a new request comes in, the algorithm discards all timestamps from the log that are older than the defined time window.

After cleaning the log, the algorithm simply checks if the number of remaining timestamps is within the set **limit**. If it is, the request is allowed, and its current timestamp is added to the log. Otherwise, the request is denied. This approach perfectly avoids the "boundary problem" of the Fixed Window Counter. The trade-off is that it can be very memory-intensive, especially with a high request volume, because it has to store a timestamp for every request.

**Walkthrough Example**

Let's use a **limit of 3** requests and a **window of 60 seconds**.

- **Time t=10s:** Request 1 arrives. The log is empty, so the request is **Allowed**. The log is now `[10s]`.

- **Time t=20s:** Request 2 arrives. The log has `1` entry, which is `< 3`. The request is **Allowed**. The log is now `[10s, 20s]`.

- **Time t=50s:** Request 3 arrives. The log has `2` entries, which is `< 3`. The request is **Allowed**. The log is now `[10s, 20s, 50s]`.

- **Time t=65s:** Request 4 arrives. The current window is `65s - 60s = 5s`. We discard all timestamps older than `5s`. The log is now `[10s, 20s, 50s]`. The size is `3`, which is `== 3`. The request is **Denied**.

- **Time t=75s:** Request 5 arrives. The current window is `75s - 60s = 15s`. The timestamp `10s` is now too old, so it's removed. The log is now `[20s, 50s]`. The size is `2`, which is `< 3`. The request is **Allowed**. The new log is `[20s, 50s, 75s]`.



## Sliding Window Counter

This algorithm is a clever middle ground that balances the accuracy of the log method with the memory efficiency of the fixed counter. It doesn't store every timestamp. Instead, it maintains a counter for the current window and the previous window.

When a request arrives, the algorithm calculates a **weighted count** based on how much of the previous window is still relevant. For example, if you are 25% of the way into the new window, you'd add the full count from the current window plus 75% of the count from the previous window. The total weighted count is then compared against the **limit**. This approach effectively "smooths" the rate limit, preventing the sharp burst problem of the Fixed Window Counter.

**Walkthrough Example**

Let's use a **limit of 5** requests and a **window of 60 seconds**.

- **Time t=0s:** The first window starts. `currentCount = 0`, `previousCount = 0`.
- **Time t=20s:** 3 requests arrive. `currentCount` becomes **3**.
- **Time t=55s:** 2 more requests arrive. `currentCount` becomes **5**.
- **Time t=65s:** A new request arrives. The new window has started.
    * The old `currentCount` (5) becomes the `previousCount`.
    * The new `currentCount` is now **1**.
    * We are `5s` into the new `60s` window (`65s - 60s`).
    * The percentage of the previous window still relevant is `(60 - 55) / 60 = 5/60`, or about **8.3%**.
    * The **weighted count** is `(previousCount * percentageOfPrevWindow) + currentCount`.
    * `weightedCount = (5 * 0.083) + 1 = 0.415 + 1 = 1.415`.
- The `weightedCount` `1.415` is `< 5`. The request is **Allowed**.

