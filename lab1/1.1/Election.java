package lr1;
import java.util.*;
import java.io.*;



public class Election {

      public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          int t = Integer.parseInt(br.readLine());
          br.readLine();

          for (int i = 0; i < t; i++) {
              if (i > 0) {
                  System.out.println();
              }
              solve(br);
          }
      }

      static void solve(BufferedReader br) throws IOException {
          int n = Integer.parseInt(br.readLine());
          candidatesCount = n;
          List<String> candidates = new ArrayList<>();
          for (int j = 0; j < n; j++) {
              candidates.add(br.readLine());
          }

          List<List<Integer>> ballots = new ArrayList<>();
          String line;
          while ((line = br.readLine()) != null && !line.isEmpty()) {
              String[] ballotStr = line.split(" ");
              List<Integer> ballot = new ArrayList<>();
              for (String s : ballotStr) {
                  ballot.add(Integer.parseInt(s));
              }
              ballots.add(ballot);
          }


          Set<Integer> activeCandidates = new HashSet<>();
          for (int j = 1; j <= n; j++) {
              activeCandidates.add(j);
          }

          String winner = null;
          List<String> tiedCandidates = null;

          while (winner == null && tiedCandidates == null) {
              Map<Integer, Integer> counts = countFirstPreferences(activeCandidates, ballots);
              int totalVotes = 0;
              for (int count : counts.values()) {
                  totalVotes += count;
              }

              int maxVotes = 0;
              for (int candidateIndex : counts.keySet()) {
                  maxVotes = Math.max(maxVotes, counts.get(candidateIndex));
              }

              if (maxVotes > (double) totalVotes / 2) {
                  for (int candidateIndex : counts.keySet()) {
                      if (counts.get(candidateIndex) == maxVotes) {
                          winner = candidates.get(candidateIndex - 1);
                          break;
                      }
                  }
                  break;
              }


              int minVotes = Integer.MAX_VALUE;
              for (int candidateIndex : counts.keySet()) {
                  if (counts.get(candidateIndex) > 0) {
                      minVotes = Math.min(minVotes, counts.get(candidateIndex));
                  }
              }

              boolean allTied = true;
              if (activeCandidates.size() > 0) {
                  for (int c : activeCandidates) {
                      if (counts.getOrDefault(c, 0) != minVotes && counts.keySet().contains(c)) {
                          allTied = false;
                          break;
                      }
                  }
              }

              if (allTied) {
                  tiedCandidates = new ArrayList<>();
                  List<Integer> sortedActive = new ArrayList<>(activeCandidates);
                  Collections.sort(sortedActive);
                  for (int c : sortedActive) {
                      tiedCandidates.add(candidates.get(c - 1));
                  }
                  break;
              }


              List<Integer> losers = new ArrayList<>();
              for (int candidateIndex : counts.keySet()) {
                  if (counts.get(candidateIndex) == minVotes) {
                      losers.add(candidateIndex);
                  }
              }

              for (int loser : losers) {
                  activeCandidates.remove(loser);
              }
              
              if (activeCandidates.isEmpty()) {
                  tiedCandidates = candidates;
                  break;
              }
          }
        if (winner != null) {
            System.out.println(winner);
        } else if (tiedCandidates != null) {
            Collections.sort(tiedCandidates);
            for (String candidate : tiedCandidates) {
                System.out.println(candidate);
            }
        }
      }

      static Map<Integer, Integer> countFirstPreferences(Set<Integer> activeCandidates, List<List<Integer>> ballots) {
          Map<Integer, Integer> counts = new HashMap<>();
         
          for (int i = 1; i <= candidatesCount; i++) {
              counts.put(i, 0);
          }

          for (List<Integer> ballot : ballots) {
              for (int candidateIndex : ballot) {
                  if (activeCandidates.contains(candidateIndex)) {
                      counts.put(candidateIndex, counts.get(candidateIndex) + 1);
                      break;
                  }
              }
          }
          return counts;
      }
       static int candidatesCount = 0;
  }
