package com.stackroute.pie.service;

import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.domain.SearchPDM;
import com.stackroute.pie.exception.PolicyNotFoundException;
import com.stackroute.pie.repository.SearchRepository;
import com.stackroute.pie.repository.SearchValueRepository;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service
@CacheConfig(cacheNames = "Policy")
public class SearchServiceImpl implements SearchService {


    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository, SearchValueRepository searchValueRepository) {
        this.searchRepository = searchRepository;
        this.searchValueRepository = searchValueRepository;
    }

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SearchValueRepository searchValueRepository;

    SearchPDM searchvalue = new SearchPDM();

    int count;

    @Override
    public List<Policy> getAllPolicies(String value) {
        List<Policy> policy1 = new ArrayList<>();


        List<Policy> pol = searchRepository.findAll();

        for (Policy p : pol) {
            List<String> tok = p.getDiseasesCovered();
            for (int t = 0; t < tok.size(); t++) {
                if (tok.get(t).equals(value)) {
                    policy1.add(p);
                }
            }
        }
        return policy1;
    }





    @Override
    public Policy savePolicy(Policy policy) {
        return searchRepository.save(policy);
    }

    public SearchPDM saveSearch(Policy policies) {

        if (searchValueRepository.existsById(policies.getPolicyName())) {
            searchvalue = searchValueRepository.findBySearchValue(policies.getPolicyName());
            count = searchvalue.getCount();
            searchvalue.setSearchValue(searchvalue.getSearchValue());
            searchvalue.setCount(++count);
        } else {
            count = 0;
            searchvalue.setSearchValue(policies.getPolicyName());
            searchvalue.setCount(++count);
        }
              saveCount(searchvalue);
        return searchvalue;
    }


    public SearchPDM saveCount(SearchPDM searchPDM) {

        return searchValueRepository.save(searchPDM);
    }

    protected static final String[] stopwords = {".", "a", "as", "able", "about", "above", "according", "across", "actually", "after", "afterwards", "again",
            "against", "age", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an",
            "and", "another", "any", "anybody", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appropriate",
            "are", "arent", "around", "as", "aside", "ask", "associated", "at", "available", "away", "be", "became", "because", "become",
            "becomes", "becoming", "been", "before", "behind", "being", "below", "beside", "besides", "best", "better", "between", "both", "brief", "but", "by", "came", "can", "cant", "cannot", "cause", "causes", "certain", "changes",
            "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course",
            "currently", "described", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "during", "each",
            "eg", "eight", "either", "else", "enough", "entirely", "especially", "even", "ever", "every", "everybody", "everyone", "everything",
            "ex", "exactly", "example", "except", "far", "few", "fifth", "first", "five", "for", "forth",
            "four", "from", "further", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "had", "hadnt", "happens",
            "hardly", "has", "hasnt", "have", "havent", "having", "he", "hello", "help", "hence", "her", "here", "heres", "hers", "herself",
            "hi", "him", "himself", "his", "how", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "indicate", "indicated", "indicates", "inner", "instead", "into", "inward", "is", "isnt", "it", "its", "itself", "just", "keep", "keeps",
            "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "let", "lets", "like", "liked", "likely", "little", "look", "looking",
            "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely",
            "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "nor", "normally", "not",
            "nothing", "now", "of", "off", "often", "ok", "okay", "old", "on", "once", "one", "ones", "only", "or", "other", "others", "otherwise",
            "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "probably", "provides", "quite", "rather", "rd", "rs", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same",
            "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sent", "serious", "seriously", "seven",
            "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon",
            "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the",
            "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre",
            "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried",
            "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually",
            "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever",
            "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom",
            "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero",
            "policy", "disease", "diseases", "policies"};


    protected static final Set<String> stopWordSet = new HashSet(Arrays.asList(stopwords));

    @Cacheable(value = "policies")
    public List<Policy> getByDisease(String disease) {
        return searchRepository.findByDiseasesCovered(disease);
    }

    @Cacheable(value = "Insurer")
    List<Policy> findByInsurerName(String insurerName) {
        return searchRepository.findByInsurerName(insurerName);
    }

    @Cacheable(value = "policies")
    List<Policy> findByPolicyName(String policyName) {
        return searchRepository.findByPolicyName(policyName);
    }

    @Cacheable(value = "findAll")
    List<Policy> findAll() {
        return searchRepository.findAll();
    }

    protected static final String[] policyArr ={"starmaxchildcare", "starmaxoptima" ,"starmaxeasyhealth" ,"sunlifechildcare" ,"sunlifeoptima" , "sunlifeeasyhealth" , "happylifechildcare","happylifeoptima","happylifeeasyhealth","futurelifechildcare","futurelifeeasyhealth","futurelifeoptima","maxlifechildcare","maxlifeoptima","maxlifeeasyhealth"};

    protected static final Set<String> policyString = new HashSet(Arrays.asList(policyArr));

    String companyString = "maxlife,starmax,futurelife,happylife,sunlife";
    String diseaseString = " cancer, diabetes, aids, dengue, malaria, tuberculosis, cardiac, heartattack, surgery,cataract";


    public List<Policy>checkValue(int val){
       List<Policy> addPolicy = new ArrayList<>();
        if (val > 1000) {
           List<Policy> policies=searchRepository.findAll();
                for (Policy p : policies) {
                    if (p.getMaxSumInsured() >= val && p.getMinSumInsured() <= val)
                        addPolicy.add(p);
                    searchvalue = saveSearch(p);
                }
            return addPolicy;
        }else if (val < 100) {
            List<Policy> policyList = findAll();
            for (Policy p : policyList) {
                if (val < p.getMaxAge() && val > p.getMinAge()) {
                    addPolicy.add(p);
                    searchvalue = saveSearch(p);
                }
            }
        }
        return addPolicy;
    }

    public List<Policy> checkString(String string)
    {
        if (policyString.contains(string)) {
            List<Policy> policies2 = findByPolicyName(string);
            for (Policy p : policies2) {
                searchvalue = saveSearch(p);
            }
            return policies2;
        } else if (companyString.contains(string)){
            List<Policy> policies2 = findByInsurerName(string);
            for (Policy t : policies2) {
                searchvalue = saveSearch(t);
            }
            return policies2;
        }
        else if (diseaseString.contains(string)) {
           List<Policy> policies = findAll();
           List<Policy> policies3=new ArrayList<>();
            for (int i=0;i<policies.size();i++) {
                List<String> str=policies.get(i).getDiseasesCovered();
                if(str.contains(string))
                {
                    policies3.add(policies.get(i));
                }

                searchvalue = saveSearch(policies.get(i));
            }
            return policies3;
        }
        else{
            return Collections.emptyList();
        }
    }

    public List<Policy> checkSearchCombination(String string,int num)
    { List<Policy> addPolicy = new ArrayList<>();
        if (policyString.contains(string) && num <=100) {
            List<Policy> arrayPolicy = findByPolicyName(string);
            for (Policy pol : arrayPolicy) {
                if (pol.getMinAge() < num && pol.getMaxAge() > num) {
                    addPolicy.add(pol);
                    searchvalue = saveSearch(pol);
                }
            }

        }
        else if (policyString.contains(string) && num >= 1000) {
            List<Policy> arrayPolicy = findByPolicyName(string);
            for (Policy pol : arrayPolicy) {
                if (pol.getMinSumInsured()<num && pol.getMaxSumInsured()>num) {
                    addPolicy.add(pol);
                    searchvalue=saveSearch(pol);
                }
            }
            return addPolicy;
        } else if (diseaseString.contains(string) && num > 1000) {
            List<Policy> policies=findAll();
            for(Policy p:policies) {
                if (p.getDiseasesCovered().contains(string) && (p.getMinSumInsured() < num && p.getMaxSumInsured() > num) ) {
                    addPolicy.add(p);
                    searchvalue = saveSearch(p);
                }
            }
            return addPolicy;
        }
        else if (diseaseString.contains(string) && num < 100) {
           List<Policy> policies =findAll();
            for (Policy p : policies) {
                if (p.getDiseasesCovered().contains(string) && (p.getMinAge() < num && p.getMaxAge() > num)) {
                    addPolicy.add(p);
                    searchvalue = saveSearch(p);
                }
            }
            return addPolicy;
        }
        else if (companyString.contains(string) && num < 100) {
            List<Policy> policies = findAll();
            for (Policy p : policies) {
                if (p.getInsurerName().contains(string) && (p.getMinAge() < num && p.getMaxAge() > num) ) {
                    addPolicy.add(p);
                    searchvalue = saveSearch(p);
                }
            }
            return addPolicy;
        }
        else if (companyString.contains(string) && num > 1000) {
            List<Policy> policies=findAll();
            for(Policy p:policies) {
                if (p.getInsurerName().contains(string) && (p.getMinSumInsured() < num && p.getMaxSumInsured() > num )) {
                    addPolicy.add(p);
                    searchvalue = saveSearch(p);
                }
            }
                   }
        return addPolicy;
    }

    @Cacheable("policies")
    public List<Policy> tokenString(String value) throws IOException,PolicyNotFoundException {
        List<String> tokenList = new ArrayList<>();
        List<Policy> policies ;
        List<String> newpolicy1 = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String[] words = value.split("\\s+");
        for (String word : words) {
            if (word.isEmpty()) continue;
            if (stopWordSet.contains(word)) continue; //remove stopwords
            result.append(word);
            result.append(" ");
        }

        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        InputStream inputStream = getClass().getResourceAsStream("/en-pos-maxent.bin");
        POSModel model = new POSModel(inputStream);
        POSTaggerME tagger = new POSTaggerME(model);
        String result1=result.toString();
        String[] token1 = tokenizer.tokenize(result1);
        String[] tags = tagger.tag(token1);
        int j = 0;
        POSSample sample = new POSSample(token1, tags);
        String[] strings = sample.toString().split(" ");
        int len = strings.length;
        if (len == 1) {
            List<Policy> resPolicy=new ArrayList<>();
            if (strings[0].contains("CD")) {
               j = Integer.parseInt(strings[0].split("_")[0]);
                resPolicy=checkValue(j);

            } else if (strings[0].contains("NN")) {
                tokenList.add(strings[0].toLowerCase().split("_")[0]);
                resPolicy = checkString(tokenList.get(0));
            }
            return resPolicy;
        } else if (len == 2) {
            for (int in = 0; in < strings.length; in++) {
                if (strings[in].contains("NN") && strings[1].contains("CD")) {
                    List<Policy> resPolicy= new ArrayList<>();
                    newpolicy1.add(strings[in].split("_")[0].toLowerCase());
                    newpolicy1.add(strings[1].split("_")[0]);
                    for (int t = 0; t < newpolicy1.size(); t++) {
                        for (int b = t + 1; b < newpolicy1.size(); b++) {
                            int num = Integer.parseInt(newpolicy1.get(b));
                            resPolicy =  checkSearchCombination(newpolicy1.get(t),num);
                        }
                    }
                    return resPolicy;
                } else if (strings[in].contains("CD") && strings[1].contains("NN")) {
                    List<Policy> resPolicy = new ArrayList<>();
                    newpolicy1.add(strings[in].split("_")[0]);
                    newpolicy1.add(strings[1].split("_")[0].toLowerCase());
                    for (int t = 0; t < newpolicy1.size(); t++) {
                        for (int b = t + 1; b < newpolicy1.size(); b++) {
                            int num = Integer.parseInt(newpolicy1.get(t));
                            resPolicy=checkSearchCombination(newpolicy1.get(b),num);
                        }
                    }
                    return resPolicy;
                } else if (strings[in].contains("NN")) {
                    tokenList.add(strings[in].split("_")[0].toLowerCase());
                }

                for (int i = 0; i < tokenList.size(); i++) {
                    for (int k = i + 1; k < tokenList.size(); k++) {
                        List<Policy> addPolicy = new ArrayList<>();
                        if (policyString.contains(tokenList.get(i)) && diseaseString.contains(tokenList.get(i + 1))) {
                            policies = findAll();
                            for (Policy p : policies) {
                                if (p.getDiseasesCovered().contains(tokenList.get(k)) && (p.getPolicyName().equals(tokenList.get(i)))) {
                                        addPolicy.add(p);
                                        searchvalue = saveSearch(p);
                                }
                            }
                            return addPolicy;
                        } else if (diseaseString.contains(tokenList.get(i)) && policyString.contains(tokenList.get(k))) {
                            policies = findAll();
                            for (Policy p : policies) {
                                if ((p.getDiseasesCovered().contains(tokenList.get(i))) && (tokenList.get(k).equals(p.getPolicyName()))){
                                        addPolicy.add(p);
                                        searchvalue = saveSearch(p);
                                }
                            }
                            return addPolicy;
                        } else if (diseaseString.contains(tokenList.get(i)) && companyString.contains(tokenList.get(k))) {
                            List<Policy> newPolicy = findAll();
                            for (Policy t : newPolicy) {
                                if (t.getDiseasesCovered().contains(tokenList.get(i)) && (tokenList.get(k).equals(t.getInsurerName()))) {
                                        addPolicy.add(t);
                                        searchvalue = saveSearch(t);
                                }
                            }
                            return addPolicy;
                        } else if (companyString.contains(tokenList.get(i)) && diseaseString.contains(tokenList.get(k))) {
                            List<Policy> newPolicy = findAll();
                            for (Policy t : newPolicy) {
                                if ((tokenList.get(i).equals(t.getInsurerName())) && (t.getDiseasesCovered().contains(tokenList.get(k)))) {
                                        addPolicy.add(t);
                                        searchvalue = saveSearch(t);
                                }
                            }
                            return addPolicy;

                        } else if (companyString.contains(tokenList.get(i))) {
                            List<Policy> policies2 = findByInsurerName(tokenList.get(i));
                            for (Policy p : policies2) {
                                if (p.getPolicyName().equals(tokenList.get(k))) {
                                    addPolicy.add(p);
                                    searchvalue = saveSearch(p);
                                }

                            }
                            return addPolicy;
                        } else if (policyString.contains(tokenList.get(i))) {
                            List<Policy> policies2 = findByPolicyName(tokenList.get(i));
                            for (Policy p : policies2) {
                                if (tokenList.get(k).equals(p.getInsurerName())) {
                                    addPolicy.add(p);
                                   searchvalue = saveSearch(p);
                                }
                            }
                            return addPolicy;

                        }


                    }

                }

            }


        }
        else if(len==3) {
            StringBuilder res = new StringBuilder();
            List<Policy> addPolicy = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                if (strings[i].contains("NN")) {
                    res.append(strings[i].split("_")[0]);
                }
            }
            String s1 = res.toString();
            if (policyString.contains(s1)) {
                addPolicy = findByPolicyName(s1);
            }
            return addPolicy;

        }

        else{
            throw new PolicyNotFoundException("Sorry,Policy Not Found");
        }
        return  Collections.emptyList();
    }
}











