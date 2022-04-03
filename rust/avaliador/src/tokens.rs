
use regex::Regex;

pub fn generate_tokens(text: &str) ->  Vec<String> {
    let mut _tokens: Vec<String> = Vec::<String>::new();
    let re = Regex::new(r"\s?(-?\d+|[+*\-/()])\s?").unwrap();

    for cap in re.captures_iter(text) {
        _tokens.push(String::from(&cap[1]));
    }

    // println!("{:?}",&_tokens);

    _tokens
}

#[cfg(test)]
mod tests {
    use super::generate_tokens;

    #[test]
    fn fat_test(){
        assert_eq!(vec!["(", "1", "+", "2", "+", "3", ")", "*", "43"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         generate_tokens("(1 + 2 + 3) * 43"));
        
        assert_eq!(vec!["31", "*", "(", "4", "+", "10", ")"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         generate_tokens("31 * (4 + 10)"));
         
        assert_eq!(vec!["1", "+", "3"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         generate_tokens("1 + 3"));

        assert_eq!(vec!["1", "+", "2", "+", "3", "*", "4"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         generate_tokens("1 + 2 + 3 * 4"));

        assert_eq!(vec!["1", "+", "-2", "+", "3", "*", "4"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         generate_tokens("1 + -2 + 3 * 4"));

        assert_eq!(vec!["4", "/", "2", "+", "7"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         generate_tokens("4 / 2 + 7"));


    }
}
