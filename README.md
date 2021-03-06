# ParrotTalk

ParrotTalk is an encrypted connection framework. Currently allowing anonymous 2048-bit key negotiation to establish user-provided encryption cipher and user-provided encoding and decoding, both through a provided SessionAgentMap to a starting SessionAgent server. Please look in the test case ThunkHelloWorldTest for building these maps and running a connection iwth data passing after encryption is established. There is a 4-way negotiation, from ProtocolOffered/Accepted to Go/GoToo. In using RSA 2048 signature validation and DH 2048 primes to establish the key used within the selected Cipher. The Cipher and Encoder are selected by name through the negotiation protocol. Currently three Ciphers are selectable, AESede, DESede, and DES. There are two encoders tested, asn1der, and Bytes. This protocol is described here, in this document.

https://github.com/ZiroZimbarra/callistohouse/blob/master/docs/ParrotTalkFrameDesign-3.5.pdf

For as to use cases, this encrypted connection has no third party, man-in-the-middle situation by not using Certificates. As such, this is a tight implementation of NSA-proof encryption without explicit authorization beyond knowledge of a host:port. The use cases involve any communication desired to be encrypted with such high encryption. The support will last my lifetime, so we have a settled solution, here in the third version, provided here. It requires version 111 of Cryptography, as a prerequisite. Both run on Squeak and Pharo.

http://www.squeaksource.com/Cryptography/Cryptography-zzz.111.mcz
http://www.squeaksource.com/Cryptography/ParrotTalk-HenryHouse.3.mcz

The current use is with my hubbub system, a promise-based distributed object implementation. I am working to bring ParrotTalk to Java and allow hubbub to operate interdependently between Squeak, Pharo, Java and any other languages which can support ParrotTalk and STON. My latest efforts with hubbub are to bring STON as the Layer 6 encoding. Hubbub depends on eLinda.

http://www.squeaksource.com/Cryptography/elinda-HenryHouse.14.mcz
http://www.squeaksource.com/Oceanside/hubbub-HenryHouse.38.mcz

Hubbub currently fails to load correctly in Pharo. In Squeak the two ParrotRemoteTestCases: LookupTestCase and OperationalTestCase really screw everything up, do not save an image after running. I never run these tests until I get a better handle of serialization. Currently, do to a shift in progress to STON, the serialization tests fail, just the way we like it. Fix the tests then the system starts to work right.

The shift away from vatId authorization should not adversely affect hubbub, we hope! When STON starts to work, and I think I need to turn off jsonMode, then traffic between vats can start to be analyzed and figure out how resolving is working with redirectors invoked remotely.
