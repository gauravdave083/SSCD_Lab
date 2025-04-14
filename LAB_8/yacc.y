%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void yyerror(const char *s);
%}

%union {
    char* str;
}

%token <str> ID NUM
%token PLUS MINUS MUL DIV LPAREN RPAREN

%left PLUS MINUS
%left MUL DIV
%left UMINUS

%type <str> expr

%%

program:
    expr { printf("Valid expression.\n"); }
    ;

expr:
    expr PLUS expr   { printf("+ "); }
  | expr MINUS expr  { printf("- "); }
  | expr MUL expr    { printf("* "); }
  | expr DIV expr    { printf("/ "); }
  | LPAREN expr RPAREN
  | MINUS expr %prec UMINUS { printf("Unary - "); }
  | ID   { printf("%s ", $1); }
  | NUM  { printf("%s ", $1); }
  ;

%%

void yyerror(const char *s) {
    fprintf(stderr, "Syntax error: %s\n", s);
}

int main() {
    printf("Enter an arithmetic expression:\n");
    yyparse();
    return 0;
}
