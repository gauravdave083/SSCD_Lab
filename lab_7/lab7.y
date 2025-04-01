%{
#include <stdio.h>
#include <stdlib.h> // Required for exit()
extern int yylex();
extern int yywrap();
void yyerror(const char *str);
%}

%token WH IF DO FOR OP CP OCB CCB CMP SC ASG ID NUM COMMA OPR

%%

start: sif { printf("VALID STATEMENT\n"); };

sif: IF OP cmpn CP stmt
    | WH OP cmpn CP stmt { printf("Valid while loop\n"); }
    | FOR OP cmpn SC stmt { printf("Valid for loop\n"); }
    ;

cmpn: ID CMP ID 
    | ID CMP NUM;

stmt: ID ASG ID OPR ID SC 
    | ID ASG ID OPR NUM SC 
    | ID ASG NUM OPR ID SC 
    | ID ASG NUM OPR NUM SC 
    | ID ASG ID SC 
    | ID ASG NUM SC
    | DO stmt { printf("Valid do-while loop\n"); }
    ;

%%

void yyerror(const char *str) {
    fprintf(stderr, "Error: %s\n", str);
    exit(1); // Stop execution on error
}

int main() {
    printf("Enter a statement:\n");
    yyparse();
    return 0; // Return 0 for successful execution
}
