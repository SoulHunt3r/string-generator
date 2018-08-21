/*
 *    Copyright 2018 Konstantin Fedorov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ru.dfkzbt.util.string.generator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.1.0
 * Created on 20.08.2018.
 */
public class StringGeneratorTest {
    private final static Logger logger = LoggerFactory.getLogger(StringGeneratorTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = IllegalStateException.class)
    public void testNotSetPatternGenerateException() {
        String generatedString = StringGenerator.create()
                .generate();
    }

    @Test(expected = IllegalStateException.class)
    public void testMoreThanAllowedPatternGenerateException() {
        String generatedString = StringGenerator.create()
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_LOWERCASE)
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_UPPERCASE)
                .generate();
    }

    /*
    @Test
    public void testGeneratedStringNotNullDefaultSettingsUsingBuilder() {
        String generatedString = StringGenerator
                .builder()
                .build()
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_LOWERCASE)
                .generate();

        assertNotNull(generatedString);
    }
*/

    @Test
    public void testGeneratedStringNotNullCtorMinSize() {
        int expected = 1;

        StringGenerator stringGenerator = StringGenerator.create(expected)
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_LOWERCASE);

        String generatedString = stringGenerator.generate();

        assertNotNull(generatedString);
    }

    @Test
    public void testIsSetMinSize() {
        int expected = 1;

        StringGenerator stringGenerator = StringGenerator.create(expected);

        int actual = stringGenerator.getGeneratorStringLength();
        logger.debug("String size: expected= {}, actual= {}", expected, actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedStringMinSize() {
        int expected = 1;

        String generatedString = StringGenerator.create(expected)
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_LOWERCASE)
                .generate();

        int actual = generatedString.length();
        logger.debug("String size: expected= {}, actual= {}", expected, actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedStringNotNullDefaultSettingsCtorWithDefaults() {
        String generatedString = StringGenerator.create()
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_LOWERCASE)
                .generate();

        assertNotNull(generatedString);
    }

    @Test
    public void testIsSetDefaultSize() {
        int expected = 16;

        StringGenerator stringGenerator = StringGenerator.create();

        int actual = stringGenerator.getGeneratorStringLength();
        logger.debug("String size: expected= {}, actual= {}", expected, actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedStringDefaultSize() {
        int expected = 16;

        String generatedString = StringGenerator.create()
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_LOWERCASE)
                .generate();

        int actual = generatedString.length();
        logger.debug("String size: expected= {}, actual= {}", expected, actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testStringsAreGeneratedAndDifferent() {
        StringGenerator stringGenerator = StringGenerator.create()
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_LOWERCASE);

        String first = stringGenerator.generate();
        String second = stringGenerator.generate();

        logger.debug("first=  {}", first);
        logger.debug("second= {}", second);

        assertNotEquals(first, second);
    }

    @Test
    public void testStringsAreGeneratedConstSize() {
        StringGenerator stringGenerator = StringGenerator.create()
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_LOWERCASE);

        String first = stringGenerator.generate();
        String second = stringGenerator.generate();

        logger.debug("first=  {}, size= {}", first, first.length());
        logger.debug("second= {}, size= {}", second, second.length());

        assertEquals(first.length(), second.length());
    }

    @Test
    public void testPatternMatchLatinLowerCase() {
        String expectedPattern = "abcdefghijklmnopqrstuvwxyz";

        StringGenerator stringGenerator = StringGenerator.create(1024)
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_LOWERCASE);
        String generatedString = stringGenerator.generate();

        for (char ch : generatedString.toCharArray()) {
            assertNotEquals(-1, expectedPattern.indexOf(ch));
        }
    }

    @Test
    public void testPatternMatchLatinUpperCase() {
        String expectedPattern = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringGenerator stringGenerator = StringGenerator.create(1024)
                .addPredefinedPattern(StringGenerator.PredefinedPattern.LATIN_UPPERCASE);
        String generatedString = stringGenerator.generate();

        for (char ch : generatedString.toCharArray()) {
            assertNotEquals(-1, expectedPattern.indexOf(ch));
        }
    }

    @Test
    public void testPatternMatchNumbers() {
        String expectedPattern = "1234567890";

        StringGenerator stringGenerator = StringGenerator.create(1024)
                .addPredefinedPattern(StringGenerator.PredefinedPattern.NUMBERS);
        String generatedString = stringGenerator.generate();

        for (char ch : generatedString.toCharArray()) {
            assertNotEquals(-1, expectedPattern.indexOf(ch));
        }
    }
}