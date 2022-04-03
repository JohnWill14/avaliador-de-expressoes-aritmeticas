use regex::Regex;


pub fn convert_infix_expression_to_postfix(tokens: &mut Vec<String>) -> Vec<String>  {
    let mut _pilha_operacao: Vec<String> = Vec::<String>::new();
    let mut _fila_saida: Vec<String> = Vec::<String>::new();

  for token in tokens.iter() {
        if is_token_an_number(token){
            _fila_saida.push(token.to_string());
        } else if is_token_an_operation(token){

            if!_pilha_operacao.is_empty(){
                
                let mut _p = peek(&_pilha_operacao);

                while !_pilha_operacao.is_empty()
                && order_precedence(token) <= order_precedence(&_p) {
                    let c = _pilha_operacao.pop().unwrap();
                    _fila_saida.push(String::from(&c));

                     _p = peek(&_pilha_operacao);
                }

            }

            _pilha_operacao.push((&token).to_string());
        }else if token.eq("("){
            _pilha_operacao.push((&token).to_string());
        }else if token.eq(")"){
            while !_pilha_operacao.is_empty() {
                let c: String = _pilha_operacao.pop().unwrap();
                if c.eq("(") {
                    break;
                }
                _fila_saida.push(String::from(&c));
            }
        }
    }

    while !_pilha_operacao.is_empty() {
        let c = _pilha_operacao.pop().unwrap();
        _fila_saida.push(String::from(&c));
    }

    _fila_saida
}


pub fn order_precedence(token: &String) -> i32{
   if token.eq("-"){
    1
   } else if token.eq("+"){
    1
   }
   else if token.eq("*"){
    2
   }
   else if token.eq("/"){
    2
   }
   else if token.eq("("){
    -1
   }else{
       -1
   }
}

pub fn peek(pilha: &Vec<String>) -> String{
    let mut peek = pilha.last().cloned();
    match peek{
        Some(p) => peek = Some(p),
        None => peek = Some("".to_string()),
    };

    let p = peek.unwrap();
    p
}

pub fn is_token_an_operation(token: &String) -> bool{
    let re = Regex::new(r"[+*-\\]").unwrap();

    re.is_match(token)
}

pub fn is_token_an_number(token: &String) -> bool{
    let re = Regex::new(r"-?\d+").unwrap();

    re.is_match(token)
}


#[cfg(test)]
mod tests {
    use super::convert_infix_expression_to_postfix;
    use crate::tokens::generate_tokens;

    #[test]
    fn convert_infix_expression_to_postfix_test(){
        let mut tokens = generate_tokens("5 + 2 / (3 - 8) * 5 - 2");
        
        assert_eq!(vec!["5", "2", "3", "8", "-", "/", "5", "*", "+", "2", "-"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));
        
        tokens = generate_tokens("31 * 4 + 10");
        assert_eq!(vec!["31", "4", "*", "10", "+"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));
         
        tokens = generate_tokens("1 + 3");
        assert_eq!(vec!["1", "3", "+"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));

        tokens = generate_tokens("1 + 2 * 3");
        assert_eq!(vec!["1", "2", "3", "*", "+"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));

        tokens = generate_tokens("4 / 2 + 7");
        assert_eq!(vec!["4", "2", "/", "7", "+"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));

        tokens = generate_tokens("1 + 2 + 3 * 4");
        assert_eq!(vec!["1", "2", "+", "3", "4", "*", "+"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));

        tokens = generate_tokens("(1 + 2 + 3) * 4");
        assert_eq!(vec!["1", "2", "+", "3", "+", "4", "*"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));

        tokens = generate_tokens("(10 / 3 + 23) * (1 - 4)");
        assert_eq!(vec!["10", "3", "/", "23", "+", "1", "4", "-", "*"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));

        tokens = generate_tokens("((1 + 3) * 8 + 1) / 3");
        assert_eq!(vec!["1", "3", "+", "8", "*", "1", "+", "3", "/"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));
        
        tokens = generate_tokens("10 * 20 + 3 * 7 + 2 * 3 + 10 / 3 * 4");
        assert_eq!(vec!["10", "20", "*", "3", "7", "*", "+", "2", "3", "*", "+", "10", "3", "/", "4", "*", "+"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));

        tokens = generate_tokens("1 + -2 * 3");
        assert_eq!(vec!["1", "-2", "3", "*", "+"].into_iter().map(|s| s.to_string()).collect::< Vec<String> >(),
         convert_infix_expression_to_postfix(&mut tokens));
    }
}