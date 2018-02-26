package amazon.interview;
//Design a movie recommendation system for a social media platform.
//Suggest a list of movies to a user based on the frequency it has been watched by his/her friends and second degree network.
//Assume the Helper class is implemented for you.
/*
Clarifying questions: 
1. Second degree network - friends of friends, who is not directly in user's friend circle
2. How do you define frequency? - the sum of times a movie has been watched by the circle (friends and friends of friends)
*/

import java.util.List;

/*
1. What's the difference between using ArrayList/LinkedList
ArrayList is probably best for random access. For LinkedList, it's best for sequential. 
It's easier to insert into LinkedList, but when it comes to reading element from the container at random, the access time is a little longer. 

2. Can you think of a collections that keep the elements sorted?
A Binary Search Tree

3. Is there a performance difference? 
When inserting into BST, it's log(n). Inserting into a list is O(1).
Sorting is n log(n).


*/

public class Helper {
    public static List<String> getFriends(String userName);
    public static List<String> getMoviesWatched(String userName);
}


public List<String> recommendMovies(String userName) {
    
    List<String> friends = getFriends(username);

    // get a list of all friends of friends
    Set<String> friendsOfFriends = new HashSet<String>();
    for (int i = 0; i < friends.size(); ++i) {
        friendsOfFriends.addAll(getFriends(friends.get(i)));
    }
    
    // Name of the movie, frequency
    HashMap<String, Integer> histo = new HashMap<String, Integer>();
    for (int i = 0; i < friendsOfFriends.size(); ++i) {
        List<String> movies = getMoviesWatched(friendsOfFriends.get(i));
        for (int j = 0; j < movies.size(); ++j) {
            if (histo.contains(movies.get(i)) {
                histo[movies.get(i)]++;
            } else {
                histo.put(movies.get(i), 1);
            }
        }
    } 
    
    Set<Map.Entry<String, Integer>> entries = histo.entrySet();
   Collections.sort(entries, new Comparator<>{});
   // Comparator code would go here, maybe.
   {
      return entries[0].second - entries[1].second;
   }
    
    // After the entries are sorted
    List<String> moviesWatched = new ArrayList<String>();
    for (int i = 0; i < entries.size(); ++i) {
        moviesWatched.add(entries.get(i).first);
    }
        
    return moviesWatched;   
 // Implementation here 
}
