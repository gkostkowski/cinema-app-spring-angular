#!/usr/bin/env bash

# sum all exit codes from pipe exit codes array
get_pipe_exit_code() {
    arr=("$@")
    declare -i sum
    IFS=+ sum="${arr[*]}"
    echo $sum
}


SCRIPT_NAME=`basename "$0"`

DUMP_PATH="$1"
USER="$2"
PASS="$3"

if [[ -z $1 ]] || [[ -z $2 ]] || [[ -z $3 ]]; then
    echo "Usage: $SCRIPT_NAME DUMP_PATH USER PASS"
    exit 1
fi

CYPHER_SHELL_PATH=$(which cypher-shell)

if [[ -z "$CYPHER_SHELL_PATH" ]]; then
    echo "cypher-shell not installed. Cannot import dump."
    exit 1
else
    cat "$DUMP_PATH" | "$CYPHER_SHELL_PATH" -u "$USER" -p "$PASS" > /tmp/dump_import.log
    status=$(get_pipe_exit_code "${PIPESTATUS[@]}")
    if [[ "$status" -eq 0 ]]; then
        echo "Dump was imported."
        exit 0
    else
        echo "Dump was not imported."
        exit 1
    fi
fi