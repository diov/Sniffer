cmake_minimum_required(VERSION 3.4.1)

add_executable(
        netcat

        netcat/atomicio.c
        netcat/netcat.c
)

set_target_properties(
        netcat

        PROPERTIES
        OUTPUT_NAME
        "netcat.so")
