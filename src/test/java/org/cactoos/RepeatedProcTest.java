/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2020 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos;

import java.util.concurrent.atomic.AtomicInteger;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test case for {@link RepeatedProc}.
 *
 * @since 0.49.2
 * @checkstyle MagicNumberCheck (100 line)
 * @checkstyle JavadocMethodCheck (100 lines)
 */
public final class RepeatedProcTest {

    @Test
    public void runsProcMultipleTimes() throws Exception {
        final AtomicInteger atom = new AtomicInteger();
        final Proc<AtomicInteger> func = new RepeatedProc<>(
            AtomicInteger::getAndIncrement,
            3
        );
        func.exec(atom);
        new Assertion<>(
            "must run proc 3 times",
            atom.get(),
            new IsEqual<>(3)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIfZero() throws Exception {
        final Proc<Object> func = new RepeatedProc<>(
            obj -> Assert.fail("unexpected"),
            0
        );
        func.exec(new Object());
    }
}
