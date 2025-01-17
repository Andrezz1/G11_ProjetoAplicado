PGDMP  !                     }            doamais    16.2    16.0 -               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    105744    doamais    DATABASE     �   CREATE DATABASE doamais WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Portugal.1252';
    DROP DATABASE doamais;
                postgres    false                        2615    105745    doamais    SCHEMA        CREATE SCHEMA doamais;
    DROP SCHEMA doamais;
                postgres    false            �            1259    105747    beneficiario    TABLE     
  CREATE TABLE doamais.beneficiario (
    id integer NOT NULL,
    nome_representante text NOT NULL,
    contacto text NOT NULL,
    nacionalidade text DEFAULT 'Português'::text NOT NULL,
    dimensao_agregado integer NOT NULL,
    referencia text,
    notas text
);
 !   DROP TABLE doamais.beneficiario;
       doamais         heap    postgres    false    6            �            1259    105746    beneficiario_id_seq    SEQUENCE     �   CREATE SEQUENCE doamais.beneficiario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE doamais.beneficiario_id_seq;
       doamais          postgres    false    6    221                       0    0    beneficiario_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE doamais.beneficiario_id_seq OWNED BY doamais.beneficiario.id;
          doamais          postgres    false    220            �            1259    105757    levantamento    TABLE     �   CREATE TABLE doamais.levantamento (
    id integer NOT NULL,
    beneficiario_id integer NOT NULL,
    tipo_bens text NOT NULL,
    data_levantamento timestamp with time zone NOT NULL,
    created_by integer NOT NULL
);
 !   DROP TABLE doamais.levantamento;
       doamais         heap    postgres    false    6            �            1259    105756    levantamento_id_seq    SEQUENCE     �   CREATE SEQUENCE doamais.levantamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE doamais.levantamento_id_seq;
       doamais          postgres    false    6    223                        0    0    levantamento_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE doamais.levantamento_id_seq OWNED BY doamais.levantamento.id;
          doamais          postgres    false    222            �            1259    105778    turno    TABLE     �   CREATE TABLE doamais.turno (
    id integer NOT NULL,
    user_id integer NOT NULL,
    "time" timestamp with time zone NOT NULL,
    tarefas text NOT NULL
);
    DROP TABLE doamais.turno;
       doamais         heap    postgres    false    6            �            1259    105777    turno_id_seq    SEQUENCE     �   CREATE SEQUENCE doamais.turno_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE doamais.turno_id_seq;
       doamais          postgres    false    227    6            !           0    0    turno_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE doamais.turno_id_seq OWNED BY doamais.turno.id;
          doamais          postgres    false    226            �            1259    105766    users    TABLE     �   CREATE TABLE doamais.users (
    id integer NOT NULL,
    username text NOT NULL,
    name text NOT NULL,
    password text NOT NULL,
    role character varying(50) NOT NULL,
    created_on timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
    DROP TABLE doamais.users;
       doamais         heap    postgres    false    6            �            1259    105765    users_id_seq    SEQUENCE     �   CREATE SEQUENCE doamais.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE doamais.users_id_seq;
       doamais          postgres    false    225    6            "           0    0    users_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE doamais.users_id_seq OWNED BY doamais.users.id;
          doamais          postgres    false    224            �            1259    105787    visita    TABLE     w   CREATE TABLE doamais.visita (
    id integer NOT NULL,
    beneficiario_id integer NOT NULL,
    date date NOT NULL
);
    DROP TABLE doamais.visita;
       doamais         heap    postgres    false    6            �            1259    105786    visita_id_seq    SEQUENCE     �   CREATE SEQUENCE doamais.visita_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE doamais.visita_id_seq;
       doamais          postgres    false    229    6            #           0    0    visita_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE doamais.visita_id_seq OWNED BY doamais.visita.id;
          doamais          postgres    false    228            i           2604    105750    beneficiario id    DEFAULT     t   ALTER TABLE ONLY doamais.beneficiario ALTER COLUMN id SET DEFAULT nextval('doamais.beneficiario_id_seq'::regclass);
 ?   ALTER TABLE doamais.beneficiario ALTER COLUMN id DROP DEFAULT;
       doamais          postgres    false    221    220    221            k           2604    105760    levantamento id    DEFAULT     t   ALTER TABLE ONLY doamais.levantamento ALTER COLUMN id SET DEFAULT nextval('doamais.levantamento_id_seq'::regclass);
 ?   ALTER TABLE doamais.levantamento ALTER COLUMN id DROP DEFAULT;
       doamais          postgres    false    223    222    223            n           2604    105781    turno id    DEFAULT     f   ALTER TABLE ONLY doamais.turno ALTER COLUMN id SET DEFAULT nextval('doamais.turno_id_seq'::regclass);
 8   ALTER TABLE doamais.turno ALTER COLUMN id DROP DEFAULT;
       doamais          postgres    false    227    226    227            l           2604    105769    users id    DEFAULT     f   ALTER TABLE ONLY doamais.users ALTER COLUMN id SET DEFAULT nextval('doamais.users_id_seq'::regclass);
 8   ALTER TABLE doamais.users ALTER COLUMN id DROP DEFAULT;
       doamais          postgres    false    224    225    225            o           2604    105790 	   visita id    DEFAULT     h   ALTER TABLE ONLY doamais.visita ALTER COLUMN id SET DEFAULT nextval('doamais.visita_id_seq'::regclass);
 9   ALTER TABLE doamais.visita ALTER COLUMN id DROP DEFAULT;
       doamais          postgres    false    228    229    229                      0    105747    beneficiario 
   TABLE DATA           ~   COPY doamais.beneficiario (id, nome_representante, contacto, nacionalidade, dimensao_agregado, referencia, notas) FROM stdin;
    doamais          postgres    false    221   �2                 0    105757    levantamento 
   TABLE DATA           f   COPY doamais.levantamento (id, beneficiario_id, tipo_bens, data_levantamento, created_by) FROM stdin;
    doamais          postgres    false    223   �_                 0    105778    turno 
   TABLE DATA           >   COPY doamais.turno (id, user_id, "time", tarefas) FROM stdin;
    doamais          postgres    false    227   �_                 0    105766    users 
   TABLE DATA           P   COPY doamais.users (id, username, name, password, role, created_on) FROM stdin;
    doamais          postgres    false    225   �_                 0    105787    visita 
   TABLE DATA           <   COPY doamais.visita (id, beneficiario_id, date) FROM stdin;
    doamais          postgres    false    229   4`       $           0    0    beneficiario_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('doamais.beneficiario_id_seq', 1, true);
          doamais          postgres    false    220            %           0    0    levantamento_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('doamais.levantamento_id_seq', 1, false);
          doamais          postgres    false    222            &           0    0    turno_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('doamais.turno_id_seq', 1, false);
          doamais          postgres    false    226            '           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('doamais.users_id_seq', 1, true);
          doamais          postgres    false    224            (           0    0    visita_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('doamais.visita_id_seq', 7638, true);
          doamais          postgres    false    228            q           2606    105755    beneficiario pk_beneficiario 
   CONSTRAINT     [   ALTER TABLE ONLY doamais.beneficiario
    ADD CONSTRAINT pk_beneficiario PRIMARY KEY (id);
 G   ALTER TABLE ONLY doamais.beneficiario DROP CONSTRAINT pk_beneficiario;
       doamais            postgres    false    221            s           2606    105764    levantamento pk_levantamento 
   CONSTRAINT     [   ALTER TABLE ONLY doamais.levantamento
    ADD CONSTRAINT pk_levantamento PRIMARY KEY (id);
 G   ALTER TABLE ONLY doamais.levantamento DROP CONSTRAINT pk_levantamento;
       doamais            postgres    false    223            y           2606    105785    turno pk_turno 
   CONSTRAINT     M   ALTER TABLE ONLY doamais.turno
    ADD CONSTRAINT pk_turno PRIMARY KEY (id);
 9   ALTER TABLE ONLY doamais.turno DROP CONSTRAINT pk_turno;
       doamais            postgres    false    227            u           2606    105774    users pk_users 
   CONSTRAINT     M   ALTER TABLE ONLY doamais.users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);
 9   ALTER TABLE ONLY doamais.users DROP CONSTRAINT pk_users;
       doamais            postgres    false    225            {           2606    105792    visita pk_visita 
   CONSTRAINT     O   ALTER TABLE ONLY doamais.visita
    ADD CONSTRAINT pk_visita PRIMARY KEY (id);
 ;   ALTER TABLE ONLY doamais.visita DROP CONSTRAINT pk_visita;
       doamais            postgres    false    229            w           2606    105776    users uq_users_username 
   CONSTRAINT     W   ALTER TABLE ONLY doamais.users
    ADD CONSTRAINT uq_users_username UNIQUE (username);
 B   ALTER TABLE ONLY doamais.users DROP CONSTRAINT uq_users_username;
       doamais            postgres    false    225            |           2606    105793 )   levantamento fk_levantamento_beneficiario    FK CONSTRAINT     �   ALTER TABLE ONLY doamais.levantamento
    ADD CONSTRAINT fk_levantamento_beneficiario FOREIGN KEY (beneficiario_id) REFERENCES doamais.beneficiario(id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 T   ALTER TABLE ONLY doamais.levantamento DROP CONSTRAINT fk_levantamento_beneficiario;
       doamais          postgres    false    221    4721    223            }           2606    105798 "   levantamento fk_levantamento_users    FK CONSTRAINT     �   ALTER TABLE ONLY doamais.levantamento
    ADD CONSTRAINT fk_levantamento_users FOREIGN KEY (created_by) REFERENCES doamais.users(id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 M   ALTER TABLE ONLY doamais.levantamento DROP CONSTRAINT fk_levantamento_users;
       doamais          postgres    false    223    4725    225            ~           2606    105803    turno fk_turno_users    FK CONSTRAINT     �   ALTER TABLE ONLY doamais.turno
    ADD CONSTRAINT fk_turno_users FOREIGN KEY (user_id) REFERENCES doamais.users(id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 ?   ALTER TABLE ONLY doamais.turno DROP CONSTRAINT fk_turno_users;
       doamais          postgres    false    227    4725    225                       2606    105808    visita fk_visita_beneficiario    FK CONSTRAINT     �   ALTER TABLE ONLY doamais.visita
    ADD CONSTRAINT fk_visita_beneficiario FOREIGN KEY (beneficiario_id) REFERENCES doamais.beneficiario(id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 H   ALTER TABLE ONLY doamais.visita DROP CONSTRAINT fk_visita_beneficiario;
       doamais          postgres    false    4721    221    229                  x��}Ms�F���+��莰e �H ��(�� m�(��/fVA,�U6P�L����_<�^t�E�b�c6�|�cs��L|K݋v�ż@"�~�s��l�\��M����UI�di��y��ޔ��[Q,�
�ݪ�ʰ�	�"N��U[v����20n[_�_:	���ڔ�I[>�㋕I�Bgy�|�	���"����8m�}��d*�L6C\��m~���H*"��f���}����,�����۫��5��ф��.�]�+�d���*�(�}��p�8�#,�M# ^�a����s(L�Q���]��s�:��w�뫶�D�Z!����w5�]o�M������M�������M\�&�m�/վ	�Ͷ�.t1
������sݕ2�	��'T]�`Wn��\5�J�����m�t�^����n�D���	�Wmo��Uٮ�U�H��e[-�U�u�3��e�3�#���i0+�VX���{04�]Di6n16>��[��!��-��X�*���1Q�SLz�mQ�rSwT?�H��ē�M��^����������%ZeI�N���H�p������-v
��E�?V����l'f�a�N�v�tM�c��L����_K�V���溭�`Y��珿=�\�O��_�5_�����	Sh��q�TV���Q��SY�&��h��D��f���nM�J2�R��*��y�����r���ֻ�`ab�u���i*1)����}<�6��e\�������M��B*c�DA�n�Ҳ���]2�kwX�2<+7�T!�*�5�fYB�>`�[:��CXl��,�˻��Ѩ,��tj�n�1ު������ԉ$Q�2��q:����4U�ি*7�L*�p��;*�>Tم��j֫R0ʌ�=��l˝��8���e�	���ǅ)�~��pZv]�o�&8ٙ�i��7�]��
�b�E"ڄ?�;���H#���q2�-��	/���}M�	�`�&6��?*8-7[�'̜33:��׊�fYn�U��k���*ġ��p7=�arbi��,�L�4�WM���ǿ�3,�v���l��vϷ�BN���L�ÆD�W��E{���z-��^>�Ɨ\`���0^ b���t�qS�/�xu��m����� �Q��p�/��.UJ��90�Y^M�!�	W�벅�5i^@��|���dEp��۾�E��t�:�>���H�G�i����Y�8��G5!���߻����Z��MQD�vI�m>?O�'���-�Zߒ׭��U~ (��e�wB/*
�a��&�Sl":��j�璎����t]B�v��zQ~SZY�U8-��cE+�"�4��� .tb]��w��k�i9O��i�C6���xTȨAπ���4�~ѱ�F���d�U��Y�&�a&
��؉W��|}��])�����i Kܼ��v����"��{]ޯ���kv�b�U�G�4Β���u�i�u�m�ߖ]�d8D�Y�o�FT]��,���e��q�o1��C<�;l�<�T6{��#�p���R�釵N�D�Sup�@�3���H)PO1_�*||n8S��cY]5�MH9c尤߇����;x[:J�e�_��C�]��l�9`���b/���?�vU�hѯ]�h���Z�)�,|�Y�\K�1��[��Z��E��~�'�]Pe&I��3��?/D�~�.��	�Vi�bm|S��
R���KÈ�E�P(��I!v���/d��a�]7�I84	�5�~�	 ���j������T�H9<y �_1h�,N�4��^ofW���
��¿n����Ң(x��6}fx>���2��+����ې�i�D>nЂ�&4����[r�؉r����g�)2���J��IƏ��^�R�h���3 ��y�� �G:�~�v�z����]M�x'q֘h���:����ϫ�5P�.|�)�) P[��y��x�B�� v��$�|�h�|?��yϙ�V#�&��8y�(<���܎gD��� �STOT�?��?|�O����?�m� �9H��q5����.O�`�
�Ŋ�[��dQA��K_Z����=g���r�����E�n�^�pc��Q������JEy3<+u����+.p�̕�R-��	��|9|7s:S��������@�$�F���]ZTk�����x���n�Ԫ�X�	��N�� �t;���Z)�d���$^�h�?�p�Y�Z�VXBs�$�ד��z��v7��dGjd\�SK�*(eN=��C�h��?���?@���ii�?8��'���N�TYP�(��uMMC�����4L0�����z�����龂�G<�������8���J|#!�� x��y]7���[1���	>��"�U@����_U�M�<B��9L�L�S�@eX���Y��\�O����KD`8���*��<��A��	����� R0tT��!
��!�K�<�)�)L����A���$�
��l���e�/�hE,r��M#���@*
E���U
�D�3`@�=A���nY���xf{K�3�m�w
 Ӧm��I���`/��r��¹D6t���"6"�&�Ӛ�~�q#���s+K��+>�������4+2�:��N�9k�6w�ni�f��M��>$��my�� ]���dk%������4�"��MEzhLa�RF���ll�K�Y�ψ����m���Z���a��I/��4T�R����oI�ň�/�&��&�2��TO=a4{��=�~���8�%|�&|��X�����\�*�Hyp������$��g�q�s7��ܔ
���DfJQƼjG�"x��@�]x�\5^cb=�h��3����/Ĝ�A�I�	�1�F0�M�Z@��Y>�����d�����>���K{�i7x
v��,+�S�q��re���7�b/�7�|�"_��^�V>�W��9�p:u���4($�Vbɛa
����h����g�bq����Մ��c��8��ze7,M2�_�.zM2(Ԣ8�,T��}�BG��x*ye���l�d�AY������ S7@��z2kf��oL�yC��� �0��c����ξ��/��
�wpa�¨�xdx�?�����+�R*O�N���9ظ�&��y��fz6(��~��򪲎%���u�2NA)�LN�� '�ݿ�V�wcs�\�	(C0��|�j����*Wվ
_�L��5��ƀ�$��()5�K>歧�#�N�� с�l����%]>!8�q��w��`�FD'�0(])各��T��V 4Sp�bvʏ"xF�� �K,��e���Hpb	k32�?�%�Ѭ�A���i�v0N�D�)rS�/lkA�2�� �ޖ�|��5�S���*�r���d*N�q��G�����V�-~۬w�6�z�<��wp� X�F�9�\��Vj�~L�t�ϒ,���8��Y�	)��&�"-����jW�>��,KM4#��7����Bs)����)CQ���jpt|��o�{Y7�Jĭi!̦�8���1�R�TxݧCI���m�%Y�.r�͑��Qf�G9���9!������il�Z���CG*7���؜؛�f��D>��2��?Ȯ�L�K:;�!��-H��! J�nf�T����	6�	Y�gs�E��`��P�
�H��;$,���6�7%�X��5f�҆�����y�q����WSˍ�$�['_�u���;WQ"��z�
Sq����0�:�cގ5\��ՆU6��������:���_B�bbY��� ��X�U*��\��
��[|U�"��prC�(�li��V�i8�S� F�b��	DhHX������d�A>�pT�(a�(L�� Q4�T�++_�b�Y���b|˄z5��.�(r��b�W׾,�V�d��k��+s�un)<fV�{���H��t�ӡ~�
�1b�})��H[2�60�oK@pN.�qNv����v*��I���!�B����4�zy�d����4� �p	}"��4n�.Ϭ*x���yXݒ�%:���Lu�xmH�}iJ�z�o�b��$����    =��_o��;H�� ���]����"t�`��,,��EA��W,Pm��9��l�E��3�iP��ǿ8́�-�xiq��Zo@<��u�km]��+7���+�	��+ɴ�s�J5)b%�(�Q`�^S>s�b�z�35y����9"@F����0W�;yh"m%��`��I*L^Yg�U+��y�I�U:�a	�W�}��-���ܢ]�������8���J`*�12Jg<d�L��1V��M�������J[��Ff�xtO��s��<@��C%�SN*�ܥTC�����;�AB�.}3 c�NЧ��,�1D��Z�F�Y���:0W�Ua*���$�41�>��A�,8۔��T�Y��b�{*m�s��%A�0>�e2cfM;0É��'����eGS��9@5t�`��h%�u�n�c�D�/��&�b�Qƌ���O� �a!�y�Xy�Sʅ.�����n
��Av����ďab�tZD�z��b@��7=S!C"*	�oȘ`]�����Z�P1<�6ɜB�����~�}C
?�M�~�L�A��<x]o��׽�h�V��#�o�啴a|��^��|�XP_��>�y�%�x��E��i�K�tpPb�A���Y���wV5��i����}� |i�0n�G�iIfv�F�k���ڮ�H���a>�M9��|e��,=����BcK&���WTŧT���줆1ͤ�e�L���9��s�kk�oD�7�k�(r8�A`n���g<����#S$B�l�_gx]�Ψw�Nג�ο��KLտi�����&��Ő҃�5�0\(��X�!K @h˪\Fƅ�]x�쭜�r8�b��ꎍ9P����`�<'2�s�+�	�\�V��&G3��i���L���0��f� ����45�W	㱽�rӐ&�Eut��F�I�Q;�G 0ד3N�<�_9����˘Ks|��.f�#��ߖ�"�Q�a�q��3��V9�%D߅�wa�X�;�w�X�+`�ػ��F>���I,�5�2)d�'p�u����CXs���ă-���:f,7��y2��Hw-�")���]���w�l�rH2���b����ue�ԏe���ͫ�'�\$��A�R�Kc+�Zj��8|Uo,m��ĉ9��a��8�!h���$��r��b�X�L&�z��dkK��"�BS�/IT�e|6�X4�\ۧ�.}�x¾�A�S�t�i�Ls��̲'� ��j�*�4X����	F��l@�˝��^��#
�Lv3��w`�
���9&$�]����0j�)��n�	I��@G�j��b�g%�c\�:�Я��I# {Lҙf�|P1�ČI2��3}4Cg�P�|4���O��dݖe'��������S��.����YHC�.l>cy��f�ߘT*�&�5v�u�[��'�����=�ƅ������z�w����j߸�~��ۉ�a�%(���zn>J�`)��?1���d>���Y�1�[Qth���w6�+HӸޟp\y%1kWB�_�˾e�r�-̈́�9�9�e�؜e�$k���2���Zi1O�Y �A�fye˺�l��4��:1U.�ظP����F �fJM��3v���9,����>�?��Ւ$��e��-y����b��b��^Z����ȣ�ٟ����I�� ���Y@s�C-�td�6��Fz��} y,�]��,������O��@��0|%Iw��x#�z	7x�"bi� �<��=L��!�#$����Z���~���QI�@̀���K��x���4��{�5�����T����kֻ3�x���4BА�C�Q��Լ���8��\��~��^لt�UT������l"����l�:}L�����mH�IV�V�?�&v��]K
?y�|� �'�ۖ���iZ*T�z��\<�,qQ>0*����y1iq���pk1˙��4g��I���CI��1M]�a�=Y�N��퐍 ��Z^�b��羼�6xi�*�,����U��.%'����Q4�J��,+���~���m(�"A�l�f��y�Y�k�!6�a!��F�Ij��8�τw��R:a!�!8Dn�O0gL~�mx'v����V6a�y@H�kW�f�İ��[K�(��j���~��.��H���H^��g��~���Ν��-�F���P9+Sm%�p���1Y����i6����B���OS����2��F�,99�c��8	lz����l��Vq<�<�ɱ�%be\d:+�.��P������c%/9h��|
Fj&gZ�!@�G��>MZ�'.�����u߉!g��+}�C����	K��>@���r���S��g��<��]�Ch����o�8h��'sJ��}�����vk+�lO�M�ҵn['�r:�P��ﯪ��tF���l�Ϥ�/���a�8�k��_�nE��A�tb�6�f;��w�ջ�f�fB@g�&�Oj�?X���<MT��v�,�!0�@�ƶK��D�I���H����Rn���1�O���j6aŇ�5=i5��˜��ޑi��Y`�I�#��9�uK�Z��*�"������	N��N/iIUx����O�;��|���x��3nh6����(L!~V+�0����4��Z��!� �{#].��z'��R�p��U?�U��u�Le�E#PZ \H�_<�ER��f��S��>�r�|ڶZ�Պ4Me�}�l`Ƀ7;��>9�İ*2��W�_O�t�/!��X�m�d�.ࠃ��I3���zy/G?�*c�3o���kl�'|��Ld�eL<���My�ɦ��ג �@�g۬׻�d�7v�Ǘ>�'cE:O��/uHD�����_�mO�1 2�k��%�i���}B��I#I٭��3x5�z��w�e����H��-�]Nnj���S}W;Đ*�>�2]2ؤ?���Xk=KYU�!�Tf[�c�6�
�j�(�fga������A��f��U���ǳ�EP(˾`��Wh����.�¾2i4��a�_�q٭;�����r�/r�A1�x�{��T���Jj�����,a��/�m����>@���cg2�Q��0�w�]ws�ؖ��"��T��O�C�&gx}]c��cC���7xv�gw�z��6���D,:F
��,�K�5��xrh�92���|�t�7lRABz��} ?|�`�m����`��V޴��=@C�^���� �L���m��dE2Y�o 'W���>E�%YI�������NN3��{��pK}2�lB�P �@l��wPY,�b���S}�0��B8I��Cx |.�(������w4��Xȭ%�i�V�f���<3�!������*|}H��f7ۇ�&ڕJL�ӄ��|r�*���$0��C��J���r7��M{�C�(��|�f��$�%ïyZ�	��r�Z\qdL��o#�(�V>}ZXw|�Г��T����5&ux�@�^��J�7�Q���3v`�%UQ:�6=O���������bBP0�jl;<��l�<7d�'ݔX�+���I@1�)��@>�MN� �#��]�Jq2���?�Vhvn\���y������S/l�b�i9�W����!����k���u<A�pg̍<i�� ��ahr�ȷ������j��'�r��C�f|(���)A_�D�@N_�~NGnh㛳��d~&�ķ}ű_�����F�:Y��86ҵ��d�����֌�)'wQ�@�u���P༸!�Ј���Uّ���ro����fԿ8\�c���� H�?ς�[Dl���3�!��&aŽ�hT#:$x��Vc���z��諪�> �kO|�3�$��">�\��H6ؚ8��U-�^PI~s ���ƾ׫]u/�ML�;KH�/�K�ް��B�f�x~7+��R:mc!Os�h]�F�(���c��|3�8�O`Ϫeۼ)x*�l�;��=v�dc��d�� ��|�m�����f�
il��g&ٓ�$��B���� ��o�ϡ�� ��������6�{fX�m�U)��T��Î��Q�� �  *�<�v^I1��f���k(��@�!��;��� �&�i��v_|��&91 '�҃5���Jj����C��b����lr�>.�Ԑ;���3���s�2��S<,>���p�R,<f��<�Y�����������B����5�q��� ��Ζ&�@�$�xD8f8�W�|ޅG�>��-T���ޓ���4��b#��~��0t��I�nF{�M0~N;@g���)8M/>���a#�<�F����1��9jx����|��$Ds��-CM�J&c���U�zf���;i`�( �"����4x�F��-` �;l@��S���7�Mc/� d�}�M�dz���O�//O_~��B���f����>j���Χ�H��_�%� ^9v9�r'�WSMr��8�*�Q�1��q<x���Gwȧݙ+U0k���.|��?˱ ��A��WUw��X�Z0�iŤq>�Ø6�����$�b��v�"u��䏙=�"�}t<&FX�<��pl��S��B�b�:�����6���u�o�jW������*�f	�$X5���ioc�X��~�bz&x�I�	s�������Y��ɣܫi̴�.G`o��[����tf���!��$��={8�癤��4>�=A�ƶ������,��IB����<�cs]ڂ���$d��D�$�Pm� �_s�.�_m�[��e�2���"�$�m�m��"�\��x�W��o��[��L�}H�6M��ƍتn+���Nx:ly�Y�,� }`��\F�%O�c�N�gU��b^������T�D�G�aaI����G���7^�feN7��0�?˗޼��3�K7A��om���kIfZ��k��p�A��U<J���<o�[�Ė�:Ńm��k7"x�:8�����p,-<�I��ȅ	q:JA�+{�
�1-�p�܂0����࡛VN�w�F,!+�s����%ė�[��nksQ
k��ɉ�\�5׼pϺ�R��^����0�x,��-���³F695��3����$Q�LȻ�'�jǴ���I.?oW�����%�+��R�S<�w;}�Aa����c'����sU�y&���xʢI��y�q������B������׹�!���$�bzT$� |���0��N���٘'^,U[��d<����k���T<Io<;篹0�����J媠���qR��r�o2Y��P���؄*g���(����5����������	d�³7��&3§ĝ��eV���U[�qd�o&A���Г�����<��u��6=��c=��J���6��}��� d�G���P�S��,n%�����u�G��h,���J�B�Վ��=��|�����'oHN���� ޤ9Ф�GÇ�c<���.Q^�z�_��$��@�T^դ��aYu��=��`��)�1g��ƃS�[����7�k �j�Ǘ�8�۱��>�\�S�S�+iN�0���;a۝4('p�i���ҩ$���M�sAq�C�҃�L�Lpv9�H8�׮�n��(����׿�f��;��ɰ�H�yk�/��il�{^*֖�[M��#q����|Q����N���D�{���Ꮞ���+.x~ڶl��B	��"/Ա,��̨"6�<	̘��F�:�9��J��5��kw/�[�JE�О;i�*]V�d�wig���HQ���s�<����4]�!3.�1	P�KAS �l}����ȡ���?�p��?e��xs/L _����z�|Bg��x7��
��*��I�x��y��X]_J]4��`�!����!/���U9�T�a����r矻�ıv��<�P~.�H��ԃМF ?�b��&ꫛ��,�g;��e��F�C�qѬ�-b^����G�J��B���g�^Q �7o�>���fdn�ԓ�\�Gw"��H0���s����V �+`�MJJD�Q�0G��8���'���NtU[nB�ӊM���@ɤ��s�aU�k��ͅ������|'oCK�>G�]y\�[�,i�e�k��f��#��R����1u��$�uE{Gփܭ�i��'7l�}��=�X5��Z�+*x�Pb��Lu�m-��Kz���ON.���PN)�4Yby�8�����#%wH�Ϻ��Ȁ�^>��8�/les3��͏��Ѳ�W�^jL����xWN��CH*w�f�/ �dg��J<o��/H����'m�q}�Y�dE�.�kK���-�s��9���\�a{7E���)�%q��Ő7�P]v��(
�|Q3�-�ū�2���E���z6�9o�q���՟{�L nK�cu$�ffp�f�-��ݛ⟄�ꢹ�4X�}����`�\I�n9�$p�7,h��{�M�H��������%�%Ӫ0 K����
Ɋ�pe�(�8?�Q&�B7��A7�ۍW��(�V��"�`Ыd���r�1��8�bv�M8��nmߗʙ��l���U�����L���䆛�ޣ��$�馳�8^�g��aZ�nhx o�6f�<V�\�����(X��!
%�`����ѐ�JM*.�wӃ��������Ia_;g��ǲބ'7�;,<�.ʓ��:�[�ظ;�v����0�滢b^��t��`�!�L�(@aa�^�<� �9}C.sM��J��q��;,�%�S�/}C1\DL(4��yȪ�����p�yگk�Ň@�	�6����867j�-{k���m��7Ǧ�yJ� oB��h{-(�3-F����\{}� |�D�Q�v(GT��`L� �Y�NT7���(��t8�.nN�w�;�FPi�v�M�h^((�hv5Ǫ�^�-=�pt��c�n*�*���zuO��Gtt�n���WM0!a��#�j��J�Cg��z��d���f����+N��z�r(�^�LI����0�N��Q�����H��
�U<*M����Ĉ�������1�,3���'�F��s�$��(ΕJ���,��$+4x_^t��\Jnu�}/#�f0c�VZ��l�$���-���#J�Ɇ�wxx�tM
��a�f������$�D/A-�ɷr��~�K79�'D���
�ȤHs9W���p�1e�G��$ߤ�o�-��W���M�b��Md�e#��os�.�����K9R�r�����8~���-��ߒ#��#��G~3G~ˎ����x��z�Ru�;����_����)�O             x������ � �            x������ � �         i   x�-�1�0 �9~E�*�����Ɩ:��+U]n<ܢޯ���iC��F`���`Y\s��J�>��kp����E�����Hv����"��<�+����h�}�Y�            x�]�Q��
��w���JE�r�?���FYV�C���At!16i����S����_�f��_y@�����#�2~�H�+Չ=?]�*�����uM��o���A�W�5�|��&�}�&���6�o�M�����X����:��&h țH e�:��e�憀�&�R?�Y�["o<�)���M�iW�m~�x�x~m_^���c�l�ӫC6Y}1t�v����+��h���m���9��7��#��}����}�M���M�"�������D�1}�&%�T����X"�Y}��g�ƎCď�O"��&����MdZ��&3V��I�oM~ַmRׯl�c��M��`�;A�jy>�^o�7��F)?7�L-r�{1�.t[�A3ʵ��VW�hE��~�b-����yZ������x�er�K� [Wy�u�y��Q��x�+���A%f:�N(��ַ3R��Yh�� ��E��JF�UUB�X�-Gk��Tj�vy����k<To�8��(<Q����rS�Խ����]�A˫��ծ~P����(َ�0i�(mނ�a�JFaPBhDӌ��Am��kf9���E�4����W]6��\7K禺p�oY�ʙPx¥3��h�Ό.��uښ�\=��G�Oyy�p�L(<��њ�]A��kԺ�n4�W]C�)�ETdGN�WQ�B]FEy���~���\CԕT��p)�h꿺����Gӄ����r��˩��)��T�~tA�h��+jB�����MuM�q=q��٭���UU����겪�~X]WU(r��*=����I��U5#SumU��UytTWW���)���U9k�.	j ����#����B���F3|���ڹ]>go�"S�>7n�<�scBa�ύzz�	42B?V��������'/�I���Uv�O^��o��׃��>�l4��T��׈�Оj�������O(��v=�.�!��ή9hN�U���V��1T���P}9 |h�3�c�>���>���_>���ë>*�c�q�P����ʒY=�+Kf�ح�1�[m�0�ޯW`z��+r�����TwC����n��P~���nh��UwC;�=����+���	E�{;ۉ��	o�Fk���
9�ڌ�C��vEa���L^�f�䡶z�Ljk�ԧ6�����3����gOF�i�4g��Z��ݵ�	�ڍd�K�I{u�M(�ֵ���&l�km�㯸�8h�9յ�1��p�M(��kmF��k퇖nW��V9r\k'5յ�]�õ���<ƣkmB�/�ڍ��n=K���km��Z����iu�m�=�Z����0K��zM!�b�z��CX���]ѵX����X}5�`Ӱ�}��kXN��k�q:�\k�%6�Z������I��͵ָ�\k����Z��F�4�ڄ�]��vRkx���ZJ���Z�k����h�k���7�Z;�y{[�4յ�:�Is�����p��w�kmB�����vs����l����vs��Wo���B��\k?�5յ��k�Fs�j������Zۿ�&�rP�f��&�w����l���]Ol-�k����=͵������q��p��޵v���Z;x���f]�W���e�3����o�鎸W����Z;�k�0��k�8���Z�dΓ���ٮ�cl�A����%��[+3���(r9��*��>'�$?v��{�z��1\�2�YD{+���$��R��ʘ�P�z�7[	JÛ�pk��r�4���6{[v���`3���|3Il�S3%~6��w��}���[S��D�U�j6�E���c;/n>��O}�8lM�>9�&�[���1��7b1�:�&�;�X��^���o��x���K,|?`�ې��134���;���Fl^;�V��8���cb��<��>{`>���[����Af��<��>5�﷤~30�����|�%6���l�����X+�2�Ƿ��#�q{Q�W��[�Jbˎv�B��J,����f��k�޸vpi�ޑ��K�\�6��+\6���g��+Eq�~lǆ�-��P���ΈE�Q��qC=Z�\mFA:��=*қ�P��tJ��8%�h��W��P��l��#w�{C]Zx�i(L���LK�#�8J�W5�P�֫?P�VN��i��P��l���tb�~�c�/�P�5P��3_�OQ�V.���*�?b\�J��pY-�m��k��z�X�z�I���se�4��.��6C�7�zd�s=/�=�3����\�̀Aϕ_��\y�c������)�����-�w��^���r]�vT��z��,5��������k\B�����]���W[>��W����W�7���:Q�M�����o6�נ���9�Z���k~��W���XY��GC�/��ʺj����r��5�/���W=�%�e�|��s�������}�Sf�z����o�=�[���l�z=���C�+�s�������y{��o6}ߡ穂4[���5�t�yb�-nGc=���v��[��7!t�yn������=oʶA��L���:��mKl�z�N�������8���ƹ@��'�A���Bϛ�s���=�,�W[~���Su}���Cz��-N/�X�O����ޡ�s@�w)i�=��퀞Q;���!�y�Ca���s{���U-�/;��x-١�WͧCϯ
O����=7�~;����P��[�@ύ�=�lλ=^9��&����=���s�bznWl@ϭs��盭~���!:��x-١��}=O,|
=����C���v@�7��ڡ�h5s�=O��o��~�+�ߺ��/1?w�yf��yba���z�C��Y��_�牅���k�=�~�����o��~r��3����=�v@�_A�;������������3����p��wγ;���"e�3�|�Ŧ�v��fS�:�|�=W�A�wil�o���Sω�B��}:�|��%�z�+fs�Q���z�O��O��r[��]�3b%����1��z>=c@�w�n�z>RnQ�`Ǡ���8�K��=�lj��Z��GԚ����c֐μ�Ħ��Y/��sb��z̞_�`�cדּ���~B�hD�������p�oDD�Q�D#j)g�3���^��Q�8�-|5�3��s�f��5��޸�%6�5��8����G���҈z����'?7j��ڈ���E#�bϹ舵9�#��I�-~���u��r�x��n��uX��i��bݼk}�V5�nN�C�C��T����c_a���Z|`��k�+���|��2��|�}���	�q�����6�-3�X���m�o�X���k�Ģ�X�����^���`۰V�v���}nk�r�� {w�\3��{{ښ�����r�r��Zb+N��~5������������f�9�_�����9�@�X�: �
9ba]���G��
�C�����\�`����|�\��P�W��G�|m ����rr��_���l�y�p�b 7�+�oOx�v�5�6���@n \{��q"^��q� 9~	;��csm?���g��V��������l�=45mN���M�,t�ٛXh�3�C)r&�E�9����~�g-��gn��\�3�`#�����ܬ�����:��;Ù$V�uJ,ڌ9r3Y�5fh��B˜ubo�o$V�O1W)��^b����|��F+���ެ�|�9CI���8���a<'6�;=�c7�h�n��W��ݴ�/���˛���۝Ub���{�9E<c���z������6���ɹ"v�U8�{\�t�ɕۇ��u�w�V+3^�g��Xg�ö/�g5�+��R>�l$�b>�5<��OR���o�z���^��ϻ����(Gw�v�kL�m��hG{�xfKyڂ����;��5��i�<��g��X�#�y;Z�A��q	=�l� v�,j�:��zεC{�!��"��6uՙ�g�&�Ƃ��V�C�w}r����T��A��5�A��5�AϹ��vT~��k��$1�}	=og,D�A�S�q^�[��|3[υל=o'��~�s�bz����z�YIl�$�|��   ����z�Y�1=O,�=�5��s�tznIg�}��Ŕ�{Z6�q{Z�R��>;u�o����6���o�ך�b/�����oa���aL����{<>��9�s������TK�6C�;���_b��b�[탞����dnG��~nGg]�=
�]mvz�I�z��]��ރ�
쀞o6��S�S��~���=\a�|�{]����<,� �oX��r�v|��=g����������n���$����ͦ�Ļ�����w�E����yb�}�c{�{��V�����x��X�Ģ&h��x(ǟ*��7��͜��}pb�f�����~;4ٱ�x�;*��|��~��g�6����9�-�]rM��=����{��9M���ڡŻ��f������<'��%sSbCТA�,ޕ֊xW��|.���r�2~;[�B�7[mq=���~s��Y��d����X<��6��_���~ER4�%> �LV[�3Υ�B�k������=>{�X��	��=>{����vs�$>K,��/�2��)Xڳs1�{�1���Rm3�����k��`O�x��`ϥy� �뎆Z�<�z��?|�k�k^�Ga����؜�P�>l�C�yˮ�9��Ol�V�sj��@��>���5bh3��vp�z�a�/��6��07[�j��r���)�ծ���Y<�����9��ⷰ�|���=��^@-QR�3�\���u
j���~[`�>��=��Η45=bq�F��s-��}=�9���v��_y�kl�ք?6��2?�$6�jkR�\��s=�r�o�Fy	�c�-�D},3��Ie�95��V�߬�ݿ�Z>vƂ��f�m
;��Oa�sP?�R��������/:�E����Y��4�,��WU�s��E[j���AmĢ?�������X�#���v�7?�5�ak\���k��ukK�u੫X3��l���au=�������԰��i��\�^��\��fS���9I��y�&��>�<Ձ�}����؂���؀�+׹��:l���s=��؅�g?C���8���}�1��c#��~w[��a7�%X�^�=�±=�탞o�쀞'>��of�-���!��_����=�{Mg���#o�,�z�r�e$65�`f��o�M�@�&W]��(���{$}�{��V�_�8���>�[#�����~VC>C���_�f��i^��`�c4h�(�}h�f�?�ۢ'�۠�!ρ�6���^���>�c�<�h���π�W�X�N�]5�T�Y��{R:!�6<��vT���0�6�̑�͹s�\uȇrՕ1��=�Ig�?�y��1��0�y�Jq��ORM:셞�T�_����0�I|����yD��ͩy�ҎƇ_��uc��S�y;k��%6uq{��g�(���X��7�{rՆ{�j�q?��f�-��T{�Cϯz,b�5���i��|�%6�}!��}qؚ���!�G_���yb1.����h���n�
z�X/q���U�����\Z��̤]q=o׼=�{9����!�¡f������E���v�)�|��S�p3���ѿ�sc��gb�u��a��
zn'֢ߠ�W}�m�4G�e��<3�zng=~����q��a+���W]Ǟ�q�GⰖ����3fǵ��:!�zn���hb���!h���A�;�8M:�5p�t�#pZfx�C���0���dוg�p$��������3��ıh�y�`���v�u��؜#p<�쯅�N�4�|�#��?�4�ɧ�[Il�J�����҄�TK;l�z�ֺ80�0Y��0fphZf86���S�yb��pr�a���#�_��Տ������<���8u��)�|�\����$��1Vq���z{<z>����zq�z����>�9�5��&WmǨ�U�9j��w�|�ɮ��؀���p��a�}���9^��~:sB��v؊q�s�}����[��������D�æn�H5}ޫ�8���8T��e�����������r=�]��&6�3`Ge�֖؜�q�������[�V3<�=�ͽ8_���V�A�q��#�2��J,����VL6ؑ����\�5}h��ᠵ��������CJ�k3��� ���s��&6�G8nMS�6b�uL�ɉa�똦�cL��h:;1��q���Ùk�r��C�[�k����Ʊk�=
�����v�'��{�پN,l�X�e<�}���X�/�$�bh�����;x��#�t�hg��3��5��;�.���ӗ[q��:�y�a��8�-1�[��V��q[)��*�y�����8�-1�/�cKc?�c���oȶ�s|ĉl��M�}ɖ�[�vTne���g=vp�1�e�l��m�ڌ���I��n$6c<�f+��/�f+��<g۵ñ~+�}m.�،+�Ϧ��8u�i:11��6��v����"l��_��C�!m*<w┶�0?�6��>z��b�~��pR�~u�5�qT�a�W~/��^��^{/qZ��Zں�����h��N��oÁm���^mV�TX��܎����spf[f�g�y��cz~�mım�WLB��@��m���z�jP�B,�=W�ipx��܆����=�oS���o;l�8�-���-�C���k���͖��s�\
��65Ǹm��z���� 7���8�Mw}�{��Q��z�ߐs�,쀞W�禕ל8�M�Q�3��]g`���z^��z^�>��'~��W�wq��V�mq����m���3#N��׾H���\׹�ԐcA�w-�c�ؚK�����އ�g�A��rC�s>T�p�[f��yfx.���3������Fl�v\�z�ؼvp�㨷��f����N3�s;�\L��ߺ&�u�ؼ��0��3b�N�Ùoj���������b�~S����>�1���p���8h�\��I��_�U�����/���q�ഷ��X�����bn�x.�v��bn�����쀞���s;F���1�bnǸ�z>��pr�s1Kg��������3�"     