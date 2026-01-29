package com.programing;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstRepeatedAndNonRepeatedString {

	public static void repatedCharacters(String str) {
		Map<Character, Long> repeatedChars = str.chars()// IntStream of characters
				.mapToObj(c -> (char) c)// Convert int to Character
				.collect(Collectors.groupingBy(// Group by character
						Function.identity(), // Use the character itself as the key or c->c
						Collectors.counting()// Count occurrences));
				));
		// Print only repeated characters (count > 1)
		repeatedChars.entrySet().stream().filter(entry -> entry.getValue() > 1).forEach(entry -> System.out
				.println("Character '" + entry.getKey() + "' repeated " + entry.getValue() + " times"));

		// we want insertion order
		System.out.println("--> Using LinkedHashMap to preserve insertion order:");

		Map<Character, Long> repeatedChars1 = str.chars()// IntStream of characters
				.mapToObj(c -> (char) c)// Convert int to Character
				.collect(Collectors.groupingBy(// Group by character
						Function.identity(), // Use the character itself as the key
						LinkedHashMap::new, Collectors.counting()// Count occurrences));
				));
		// Print only repeated characters (count > 1)
		repeatedChars1.entrySet().stream().filter(entry -> entry.getValue() > 1).forEach(entry -> System.out
				.println("Character '" + entry.getKey() + "' repeated " + entry.getValue() + " times"));
	}

	public static void firstRepatedCharacter(String str) {

		Map<Character, Long> repeatedChars1 = str.chars()// IntStream of characters
				.mapToObj(c -> (char) c)// Convert int to Character
				.filter(ch -> !Character.isWhitespace(ch)).collect(Collectors.groupingBy(// Group by character
						Function.identity(), // Use the character itself as the key
						LinkedHashMap::new, Collectors.counting()// Count occurrences));
				));
		// Print only repeated characters (count > 1)
		Optional<Character> ch = repeatedChars1.entrySet().stream().filter(entry -> entry.getValue() > 1)
				.map(Map.Entry::getKey).findFirst();
		System.out.println("output character::" + ch.get());
	}

	public static void nonRepatedCharacters(String str) {

		Map<Character, Long> repeatedChars = str.chars().mapToObj(c -> (char) c)
				.filter(ch -> (!Character.isWhitespace(ch)))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		repeatedChars.entrySet().stream().filter(entry -> entry.getValue() == 1).forEach(entry -> System.out
				.println("Character '" + entry.getKey() + " repeated " + entry.getValue() + " times"));
	}

	

	public static void firstNonRepatedCharacter(String str) {
		Map<Character, Long> repeatedChars = str.chars().mapToObj(c -> (char) c)
				.filter(ch -> (!Character.isWhitespace(ch)))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		Optional<Character> optional = repeatedChars.entrySet().stream().filter(entry -> entry.getValue() == 1)
				.map(Map.Entry::getKey).findFirst();
		System.out.println("First repeated character " + optional.get());
	}
	
	public static void SecondNonRepatedCharacters(String str) {

		Map<Character, Long> repeatedChars = str.chars().mapToObj(c -> (char) c)
				.filter(ch -> (!Character.isWhitespace(ch)))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		Character ch = repeatedChars.entrySet().stream().filter(entry -> entry.getValue() == 1).skip(1).findFirst().get().getKey();
		System.out.println(":: "+ch);
		
	}

	public static void main(String[] args) {
		String str = "Programming";
		System.out.println("--Given string is: " + str);
		System.out.println("<------RepatedCharacters ----->");
		repatedCharacters(str);
		System.out.println("<------FirstRepatedCharacter------>");
		firstRepatedCharacter(str);
		System.out.println("-----Non Repeated Characters -----");
		nonRepatedCharacters(str);
		System.out.println("-----First repeated Character -----");
		firstNonRepatedCharacter(str);
		System.out.println("-----Second non Repeated Character -----");
		SecondNonRepatedCharacters(str) ;

	}
}
