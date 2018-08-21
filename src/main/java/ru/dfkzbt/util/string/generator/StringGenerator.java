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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.1.0
 * Created on 20.08.2018.
 */
public class StringGenerator {
    private final static int DEFAULT_GENERATOR_STRING_LENGTH = 16;

    public enum PredefinedPattern {
        LATIN_LOWERCASE,
        LATIN_UPPERCASE,
        NUMBERS,
    }

    private final static Map<PredefinedPattern, String> predefinedPatterns = new HashMap<PredefinedPattern, String>() {{
        put(PredefinedPattern.LATIN_LOWERCASE, "abcdefghijklmnopqrstuvwxyz");
        put(PredefinedPattern.LATIN_UPPERCASE, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        put(PredefinedPattern.NUMBERS, "0123456789");
    }};

    private int generatorStringLength;
    private Map<String, String> includedPatterns = new HashMap<>();

    private StringGenerator(int generatorStringLength) {
        this.generatorStringLength = generatorStringLength;
    }

    public static StringGenerator create(int generatorStringLength) {
        return new StringGenerator(generatorStringLength);
    }

    public static StringGenerator create() {
        return create(DEFAULT_GENERATOR_STRING_LENGTH);
    }

    public StringGenerator addPredefinedPattern(PredefinedPattern predefinedPattern) {
        if (!predefinedPatterns.containsKey(predefinedPattern)) {
            throw new IllegalArgumentException("no pattern for such key");
        }

        return addCustomPattern(predefinedPattern.toString(), predefinedPatterns.get(predefinedPattern));
    }

    public StringGenerator addCustomPattern(String key, String pattern) {
        if (includedPatterns.containsKey(key)) {
            throw new IllegalArgumentException("key already exist");
        }

        includedPatterns.put(key, pattern);

        return this;
    }

    /**
     * generate random String using options provided
     *
     * @return randomly generated string
     */
    public String generate() {
        if (includedPatterns.size() == 0) {
            throw new IllegalStateException("there are no patterns to generate");
        }

        // TODO multiple pattern support
        if (includedPatterns.size() > 1) {
            throw new IllegalStateException("multiple patterns not supported ATM");
        }

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        String currentPattern = includedPatterns.get(includedPatterns.keySet().toArray()[0]);

        for (int i = 0; i < generatorStringLength; i++) {
            stringBuilder.append(currentPattern.charAt(random.nextInt(currentPattern.length())));
        }

        return stringBuilder.toString();
    }

    public int getGeneratorStringLength() {
        return generatorStringLength;
    }

    public Map<String, String> getIncludedPatterns() {
        return includedPatterns;
    }

    public static Map<PredefinedPattern, String> getPredefinedPatterns() {
        return predefinedPatterns;
    }
}
