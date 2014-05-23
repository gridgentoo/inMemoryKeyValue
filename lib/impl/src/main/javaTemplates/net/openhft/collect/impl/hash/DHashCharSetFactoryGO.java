/* with
 DHash|LHash hash
 char|byte|short|int|long|float|double|obj elem
*/
/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.collect.impl.hash;

import net.openhft.collect.*;
import net.openhft.collect.set.hash.HashCharSetFactory;
import net.openhft.function.*;
import net.openhft.collect.set.hash.HashCharSet;

import java.util.Collection;
import java.util.Iterator;

import static net.openhft.collect.impl.Containers.sizeAsInt;


public abstract class DHashCharSetFactoryGO/*<>*/ extends DHashCharSetFactorySO/*<>*/ {

    public DHashCharSetFactoryGO(/* if !(float|double elem) */CharHashConfig
            /* elif float|double elem //HashConfig// endif */ conf) {
        super(conf);
    }

    @Override
    public String toString() {
        return "HashCharSetFactory[config=" + getConfig() +
                /* if obj elem */",equivalence=" + getEquivalence() +/* endif */
        "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof HashCharSetFactory) {
            HashCharSetFactory factory = (HashCharSetFactory) obj;
            return getConfig().equals(factory.getConfig())
                    /* if obj elem */
                    && NullableObjects.equals(getEquivalence(), factory.getEquivalence())
                    /* endif */;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode = hashCode * 31 + getConfig().hashCode();
        /* if obj elem */
        hashCode = hashCode * 31 + NullableObjects.hashCode(getEquivalence());
        /* endif */
        return hashCode;
    }

    /* define p1 *//* if obj elem // <E2 extends E>// endif *//* enddefine */

    /* define p2 *//* if obj elem //<E2>// endif *//* enddefine */

    /* define ep */
    /* if obj elem //<? extends E2>// elif !(obj elem) //<Character>// endif */
    /* enddefine */

    private/*p1*/ MutableDHashCharSetGO/*p2*/ shrunk(MutableDHashCharSetGO/*p2*/ set) {
        Predicate<HashContainer> shrinkCondition;
        if ((shrinkCondition = hashConf.getShrinkCondition()) != null) {
            if (shrinkCondition.test(set))
                set.shrink();
        }
        return set;
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet() {
        return newMutableSet(hashConf.getDefaultExpectedSize());
    }


    private static int sizeOr(Iterable elems, int defaultSize) {
        return elems instanceof Collection ? ((Collection) elems).size() : defaultSize;
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elements) {
        return newMutableSet(elements, sizeOr(elements, hashConf.getDefaultExpectedSize()));
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2) {
        long expectedSize = (long) sizeOr(elems1, 0);
        expectedSize += (long) sizeOr(elems2, 0);
        return newMutableSet(elems1, elems2, sizeAsInt(expectedSize));
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, Iterable/*ep*/<Character>/**/ elems3) {
        long expectedSize = (long) sizeOr(elems1, 0);
        expectedSize += (long) sizeOr(elems2, 0);
        expectedSize += (long) sizeOr(elems3, 0);
        return newMutableSet(elems1, elems2, elems3, sizeAsInt(expectedSize));
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, Iterable/*ep*/<Character>/**/ elems3,
            Iterable/*ep*/<Character>/**/ elems4) {
        long expectedSize = (long) sizeOr(elems1, 0);
        expectedSize += (long) sizeOr(elems2, 0);
        expectedSize += (long) sizeOr(elems3, 0);
        expectedSize += (long) sizeOr(elems4, 0);
        return newMutableSet(elems1, elems2, elems3, elems4, sizeAsInt(expectedSize));
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, Iterable/*ep*/<Character>/**/ elems3,
            Iterable/*ep*/<Character>/**/ elems4, Iterable/*ep*/<Character>/**/ elems5) {
        long expectedSize = (long) sizeOr(elems1, 0);
        expectedSize += (long) sizeOr(elems2, 0);
        expectedSize += (long) sizeOr(elems3, 0);
        expectedSize += (long) sizeOr(elems4, 0);
        expectedSize += (long) sizeOr(elems5, 0);
        return newMutableSet(elems1, elems2, elems3, elems4, elems5, sizeAsInt(expectedSize));
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elements,
            int expectedSize) {
        return shrunk(super.newMutableSet(elements, expectedSize));
    }


    private static /*<>*/ void addAll(MutableDHashCharSetGO/*<>*/ set,
            Iterable<? extends Character> elems) {
        if (elems instanceof Collection) {
            // noinspection unchecked
            set.addAll((Collection<? extends Character>) elems);
        } else {
            for (char e : elems) {
                set.add(e);
            }
        }
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, int expectedSize) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(expectedSize);
        addAll(set, elems1);
        addAll(set, elems2);
        return shrunk(set);
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, Iterable/*ep*/<Character>/**/ elems3,
            int expectedSize) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(expectedSize);
        addAll(set, elems1);
        addAll(set, elems2);
        addAll(set, elems3);
        return shrunk(set);
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, Iterable/*ep*/<Character>/**/ elems3,
            Iterable/*ep*/<Character>/**/ elems4, int expectedSize) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(expectedSize);
        addAll(set, elems1);
        addAll(set, elems2);
        addAll(set, elems3);
        addAll(set, elems4);
        return shrunk(set);
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, Iterable/*ep*/<Character>/**/ elems3,
            Iterable/*ep*/<Character>/**/ elems4, Iterable/*ep*/<Character>/**/ elems5,
            int expectedSize) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(expectedSize);
        addAll(set, elems1);
        addAll(set, elems2);
        addAll(set, elems3);
        addAll(set, elems4);
        addAll(set, elems5);
        return shrunk(set);
    }


    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterator/*ep*/<Character>/**/ elements) {
        return newMutableSet(elements, hashConf.getDefaultExpectedSize());
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(Iterator/*ep*/<Character>/**/ elements,
            int expectedSize) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(expectedSize);
        while (elements.hasNext()) {
            set.add(elements.next());
        }
        return shrunk(set);
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(
            Consumer</*f*/CharConsumer/*p2*/> elementsSupplier) {
        return newMutableSet(elementsSupplier, hashConf.getDefaultExpectedSize());
    }

    /* define pe *//* if !(obj elem) //char// elif obj elem //E2// endif *//* enddefine */

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(
            Consumer</*f*/CharConsumer/*p2*/> elementsSupplier, int expectedSize) {
        final MutableDHashCharSetGO/*p2*/ set = newMutableSet(expectedSize);
        elementsSupplier.accept(new /*f*/CharConsumer/*p2*/() {
            @Override
            public void accept(/*pe*/char/**/ e) {
                set.add(e);
            }
        });
        return shrunk(set);
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(/*pe*/char/**/[] elements) {
        return newMutableSet(elements, elements.length);
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSet(/*pe*/char/**/[] elements,
            int expectedSize) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(expectedSize);
        for (/*pe*/char/**/ e : elements) {
            set.add(e);
        }
        return shrunk(set);
    }

    /* if !(obj elem) */
    @Override
    public MutableDHashCharSetGO newMutableSet(Character[] elements) {
        return newMutableSet(elements, elements.length);
    }

    @Override
    public MutableDHashCharSetGO newMutableSet(Character[] elements,
            int expectedSize) {
        MutableDHashCharSetGO set = newMutableSet(expectedSize);
        for (char e : elements) {
            set.add(e);
        }
        return shrunk(set);
    }
    /* endif */

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSetOf(/*pe*/char/**/ e1) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(1);
        set.add(e1);
        return set;
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSetOf(/*pe*/char/**/ e1, /*pe*/char/**/ e2) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(2);
        set.add(e1);
        set.add(e2);
        return set;
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSetOf(/*pe*/char/**/ e1, /*pe*/char/**/ e2,
            /*pe*/char/**/ e3) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(3);
        set.add(e1);
        set.add(e2);
        set.add(e3);
        return set;
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSetOf(/*pe*/char/**/ e1, /*pe*/char/**/ e2,
            /*pe*/char/**/ e3, /*pe*/char/**/ e4) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(4);
        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        return set;
    }

    @Override
    public/*p1*/ MutableDHashCharSetGO/*p2*/ newMutableSetOf(/*pe*/char/**/ e1, /*pe*/char/**/ e2,
            /*pe*/char/**/ e3, /*pe*/char/**/ e4, /*pe*/char/**/ e5,
            /*pe*/char/**/... restElements) {
        MutableDHashCharSetGO/*p2*/ set = newMutableSet(5 + restElements.length);
        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);
        for (/*pe*/char/**/ e : restElements) {
            set.add(e);
        }
        return shrunk(set);
    }

    /* with with|without expectedSize */
    /* define arg *//* if with expectedSize //, int expectedSize// endif *//* enddefine */
    /* define apply *//* if with expectedSize //, expectedSize// endif *//* enddefine */

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(Iterable/*ep*/<Character>/**/ elements/*arg*/) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elements/*apply*/));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2/*arg*/) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elems1, elems2/*apply*/));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, Iterable/*ep*/<Character>/**/ elems3/*arg*/) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elems1, elems2, elems3/*apply*/));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, Iterable/*ep*/<Character>/**/ elems3,
            Iterable/*ep*/<Character>/**/ elems4/*arg*/) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elems1, elems2, elems3, elems4/*apply*/));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(Iterable/*ep*/<Character>/**/ elems1,
            Iterable/*ep*/<Character>/**/ elems2, Iterable/*ep*/<Character>/**/ elems3,
            Iterable/*ep*/<Character>/**/ elems4, Iterable/*ep*/<Character>/**/ elems5/*arg*/) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elems1, elems2, elems3, elems4, elems5/*apply*/));
        return set;
    }

    /* endwith */

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(Iterator/*ep*/<Character>/**/ elements) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elements));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(Iterator/*ep*/<Character>/**/ elements,
            int expectedSize) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elements, expectedSize));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(
            Consumer</*f*/CharConsumer/*p2*/> elementsSupplier) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elementsSupplier));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(
            Consumer</*f*/CharConsumer/*p2*/> elementsSupplier, int expectedSize) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elementsSupplier, expectedSize));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(/*pe*/char/**/[] elements) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elements));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSet(/*pe*/char/**/[] elements, int expectedSize) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSet(elements, expectedSize));
        return set;
    }

    /* if !(obj elem) */
    @Override
    public HashCharSet newImmutableSet(Character[] elements) {
        ImmutableDHashCharSetGO set = uninitializedImmutableSet();
        set.move(newMutableSet(elements));
        return set;
    }

    @Override
    public HashCharSet newImmutableSet(Character[] elements, int expectedSize) {
        ImmutableDHashCharSetGO set = uninitializedImmutableSet();
        set.move(newMutableSet(elements, expectedSize));
        return set;
    }
    /* endif */

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSetOf(/*pe*/char/**/ e1) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSetOf(e1));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSetOf(/*pe*/char/**/ e1, /*pe*/char/**/ e2) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSetOf(e1, e2));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSetOf(/*pe*/char/**/ e1, /*pe*/char/**/ e2,
            /*pe*/char/**/ e3) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSetOf(e1, e2, e3));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSetOf(/*pe*/char/**/ e1, /*pe*/char/**/ e2,
            /*pe*/char/**/ e3, /*pe*/char/**/ e4) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSetOf(e1, e2, e3, e4));
        return set;
    }

    @Override
    public/*p1*/ HashCharSet/*p2*/ newImmutableSetOf(/*pe*/char/**/ e1, /*pe*/char/**/ e2,
            /*pe*/char/**/ e3, /*pe*/char/**/ e4, /*pe*/char/**/ e5,
            /*pe*/char/**/... restElements) {
        ImmutableDHashCharSetGO/*p2*/ set = uninitializedImmutableSet();
        set.move(newMutableSetOf(e1, e2, e3, e4, e5, restElements));
        return set;
    }
}