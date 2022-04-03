
use regex::Regex;


pub fn solve_postfix_expression(tokens: &mut Vec<String>) -> i64  {
    let mut _pilha: Vec<String> = Vec::<String>::new();
    let mut count = 0;

    while count < tokens.len(){
        
        let token = &tokens[count];
        count+=1;

        if is_token_an_number(token){
            _pilha.push(token.to_string());
        }else{
            let filho_direita: i64 = _pilha.pop().unwrap().parse::<i64>().unwrap();
            let filho_esquerda: i64 = _pilha.pop().unwrap().parse::<i64>().unwrap();
            let mut operation = token.to_string();

            let mut result: i64 = solve_express(&filho_esquerda, &filho_direita, &operation);

            _pilha.push(result.to_string());

            let mut aux = Vec::<String>::new();
            let mut pilha_clone = _pilha.clone();
            let mut tokens_clone = tokens.clone();

            aux.append(&mut pilha_clone[0.._pilha.len()].to_vec().clone());
            aux.append(&mut tokens_clone[count..tokens.len()].to_vec().clone());

            // println!("aux: {:?}", &aux);

            println!(" {}", express_format(&mut aux));
        }
    }

    _pilha.pop().unwrap().parse::<i64>().unwrap()
}

pub fn solve_express(num1: &i64,num2: &i64, op: &String) -> i64{
    if op.eq("-"){
        num1 - num2
    } else if op.eq("+"){
        num1 + num2
    }
    else if op.eq("*"){
        num1 * num2
    }
    else {
        num1/num2
    }
}

pub fn express_format(tokens: &mut Vec<String>) -> String{
    visitToken(tokens)
}

pub fn visitToken(stack: &mut Vec<String>) -> String {
    let mut s = String::new();
    let mut token = stack.pop().unwrap();

    if !is_token_an_number(&token){
        let token_dir = peek(stack);
        let filho_dir = visitToken(stack);

        let token_esq = peek(stack);
        let filho_esq = visitToken(stack);

        if !is_token_an_number(&token_esq) && order_precedence(&token_esq) < order_precedence(&token) {
            s.push_str("(");
            s.push_str(&mut filho_esq.to_owned());
            s.push_str(")");
        }else{
            s.push_str(&mut filho_esq.to_owned());
        }
        
        s.push_str(" ");
        s.push_str(&mut token.to_owned());
        s.push_str(" ");


        if !is_token_an_number(&token_dir) && order_precedence(&token_dir) < order_precedence(&token) {
            s.push_str("(");
            s.push_str(&mut filho_dir.to_owned());
            s.push_str(")");
        }else{
            s.push_str(&mut filho_dir.to_owned());
        }       
    } else{
        s.push_str(&mut token.to_owned());
    }
   
    s
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


#[cfg(test)]
mod tests {
    use super::solve_postfix_expression;
    use super::express_format;
    use crate::tokens::generate_tokens;
    use crate::convert::convert_infix_expression_to_postfix;

    #[test]
    fn solve_postfix_expression_test(){
        let mut tokens = generate_tokens("31 * (4 + 10)");
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        
        assert_eq!(434, solve_postfix_expression(&mut parser));

        let mut tokens = generate_tokens("5 + 2 / (3 - 8) * 5 - 2");
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        
        assert_eq!(3, solve_postfix_expression(&mut parser));

        let mut tokens = generate_tokens("1 + 2 * 3");
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        
        assert_eq!(7, solve_postfix_expression(&mut parser));

        let mut tokens = generate_tokens("4 / 2 + 7");
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        
        assert_eq!(9, solve_postfix_expression(&mut parser));

        let mut tokens = generate_tokens("1 + 2 + 3 * 4");
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        
        assert_eq!(15, solve_postfix_expression(&mut parser));

        let mut tokens = generate_tokens("(1 + 2 + 3) * 4");
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        
        assert_eq!(24, solve_postfix_expression(&mut parser));
    }

    #[test]
    fn express_format_test(){
        let mut express = "31 * (4 + 10)";
        let mut tokens = generate_tokens(express);
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        assert_eq!(express, express_format(&mut parser));

        let mut express = "5 + 2 / (3 - 8) * 5 - 2";
        let mut tokens = generate_tokens(express);
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        assert_eq!(express, express_format(&mut parser));

        let mut express = "1 + 2 * 3";
        let mut tokens = generate_tokens(express);
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        assert_eq!(express, express_format(&mut parser));

        let mut express = "4 / 2 + 7";
        let mut tokens = generate_tokens(express);
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        assert_eq!(express, express_format(&mut parser));

        let mut express = "1 + 2 + 3 * 4";
        let mut tokens = generate_tokens(express);
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        assert_eq!(express, express_format(&mut parser));

        let mut express = "(1 + 2 + 3) * 4";
        let mut tokens = generate_tokens(express);
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        assert_eq!(express, express_format(&mut parser));
    }
}