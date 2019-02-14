# requestBody解密 response加密

  

    @Encrypt
    @Decrypt
    @PostMapping("/crpto")
    public String testCrpt(@RequestBody message) {
       
        return testBody;
    }

}
