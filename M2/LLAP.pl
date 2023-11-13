:- include('KB2.pl').


% Initial State Definintion
intitial_state(S):-
    S = s0(X, Y, Z, done_build1(false), done_build2(false)),
    food(X), materials(Y), energy(Z).


% Successor State Axioms
apply_action(result(A, P), S):-
    (A = b1 ; A = b2),
    apply_action(P, S_i),
    S_i = s0(X, Y, Z, done_build1(B1), done_build2(B2)),
    (
    (
        A = b1, B1 = false,
        build1(NeededFood, NeededMaterial, NeededEnergy),
        X >= NeededFood, Y >= NeededMaterial, Z >= NeededEnergy,
        S = s0(X_f, Y_f, Z_f, done_build1(true), done_build2(B2)),
        X_f is X - NeededFood, Y_f is Y - NeededMaterial, Z_f is Z - NeededEnergy
    )
    ;
    (
        A = b2, B2 = false,
        build2(NeededFood, NeededMaterial, NeededEnergy),
        X >= NeededFood, Y >= NeededMaterial, Z >= NeededEnergy,
        S = s0(X_f, Y_f, Z_f, done_build1(B1), done_build2(true)),
        X_f is X - NeededFood, Y_f is Y - NeededMaterial, Z_f is Z - NeededEnergy
    )
    ).

apply_action(result(A, P), S):-
    (A = reqf ; A = reqm ; A = reqe),
    apply_action(P, S_i),
    S_i = s0(X, Y, Z, done_build1(B1), done_build2(B2)),
    (
    (
        A = reqf,
        S = s0(X_f, Y, Z, done_build1(B1), done_build2(B2)),
        X_f is X + 1
    );
    (
        A = reqm,
        S = s0(X, Y_f, Z, done_build1(B1), done_build2(B2)),
        Y_f is Y + 1
    );
    (
        A = reqe,
        S = s0(X, Y, Z_f, done_build1(B1), done_build2(B2)),
        Z_f is Z + 1
    )
    ).

apply_action(s0, S):-
    intitial_state(S).


% Goal predicate
goal(result(A, S)):-
    apply_action(result(A, S), S_f),
    S_f = s0(_, _, _, done_build1(true), done_build2(true)).
 
ids(X,L):-
    (call_with_depth_limit(goal(X),L,R), number(R));
    (call_with_depth_limit(goal(X),L,R), R=depth_limit_exceeded,
    L1 is L+1, ids(X,L1)).













    
    











